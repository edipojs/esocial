package br.jus.tst.esocialjt.regras.evento;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.jus.tst.esocialjt.dominio.TipoEvento;
import br.jus.tst.esocialjt.evento.EventoDTO;
import br.jus.tst.esocialjt.regras.Regra;
import br.jus.tst.esocialjt.regras.RegraEmpregadorCadastrado;
import br.jus.tst.esocialjt.regras.RegraEventosTabela;
import br.jus.tst.esocialjt.regras.RegraNaoHaEventoTabelaEmFila;
import br.jus.tst.esocialjt.regras.RegraVazia;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegrasFactoryTest{
	
	@Autowired
	private RegrasFactory regrasFactory;
	
	@Test
	public void deveProverRegraInformacoesEmpregador() {
		Regra regra = getRegra(TipoEvento.S1000);
		assertThat(regra).isInstanceOf(RegraInformacoesEmpregador.class);
		assertThat(regra.regras()).extracting("class").containsOnly(
				RegraVazia.class
		);
	}
	
	@Test
	public void deveProverRegraTabelaEstabelecimento() {
		Regra regra = getRegra(TipoEvento.S1005);
		assertThat(regra).isInstanceOf(RegraTabelaEstabelecimento.class);
		assertThat(regra.regras()).extracting("class").containsOnly(
				RegraEventosTabela.class
		);
	}
	
	@Test
	public void deveProverRegraTabelaLotacao() {
		Regra regra = getRegra(TipoEvento.S1020);
		assertThat(regra).isInstanceOf(RegraTabelaLotacao.class);
		assertThat(regra.regras()).extracting("class").containsOnly(
				RegraEventosTabela.class
		);
	}

	@Test
	public void deveProverRegraTabelaCargo() {
		Regra regra = getRegra(TipoEvento.S1030);
		assertThat(regra).isInstanceOf(RegraTabelaCargo.class);
		assertThat(regra.regras()).extracting("class").containsOnly(
				RegraEventosTabela.class
		);
	}
	
	@Test
	public void deveProverRegraTabelaCarreira() {
		Regra regra = getRegra(TipoEvento.S1035);
		assertThat(regra).isInstanceOf(RegraTabelaCarreira.class);
		assertThat(regra.regras()).extracting("class").containsOnly(
				RegraEventosTabela.class
		);
	}
	
	@Test
	public void deveProverRegraTabelaFuncao() {
		Regra regra = getRegra(TipoEvento.S1040);
		assertThat(regra).isInstanceOf(RegraTabelaFuncao.class);
		assertThat(regra.regras()).extracting("class").containsOnly(
				RegraEventosTabela.class
		);
	}

	@Test
	public void deveProverRegraAdmissao() {
		Regra regra = getRegra(TipoEvento.S2200);
		assertThat(regra).isInstanceOf(RegraAdmissao.class);
		assertThat(regra.regras()).extracting("class").containsOnly(
				RegraEmpregadorCadastrado.class,
				RegraNaoHaEventoTabelaEmFila.class
		);
	}

	private Regra getRegra(TipoEvento tipo) {
		return regrasFactory.getRegra(new EventoDTO().setCodTipoEvento(tipo.getCodTipo()));
	}

}
