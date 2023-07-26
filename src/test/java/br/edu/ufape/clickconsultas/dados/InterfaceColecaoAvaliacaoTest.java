package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPaciente;
import br.edu.ufape.clickconsultas.negocios.modelo.Avaliacao;
import br.edu.ufape.clickconsultas.negocios.modelo.RegistroAvaliacao;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;

@SpringBootTest
class InterfaceColecaoAvaliacaoTest {
	@Autowired
	private InterfaceColecaoPaciente colecaoPaciente;
	@Autowired
	private InterfaceColecaoAvaliacao colecaoAvaliacao;
	@Autowired
	private InterfaceColecaoRegistroAvaliacao colecaoRegistroAvaliacao;

	@Test
	void cadastrarAvaliacaoTest() {
		long qtdAvaliacao = colecaoAvaliacao.count();
		Paciente p = new Paciente();
		Avaliacao a = new Avaliacao(4.5, "Bom atendimento.", p, new RegistroAvaliacao());

		colecaoPaciente.save(p);
		colecaoAvaliacao.save(a);
		long novaQtdAvaliacao = colecaoAvaliacao.count();

		assertEquals(qtdAvaliacao + 1, novaQtdAvaliacao);
	}
	
	@Test
	void cadastrarRegistroAvaliacaoTest() {
		long qtdRegistroAvaliacao = colecaoRegistroAvaliacao.count();
		RegistroAvaliacao r = new RegistroAvaliacao(4, 20);

		colecaoRegistroAvaliacao.save(r);
		long novaQtdRegistroAvaliacao = colecaoRegistroAvaliacao.count();

		assertEquals(qtdRegistroAvaliacao + 1, novaQtdRegistroAvaliacao);
	}

	@Test
	void calcularAvaliacaoMediaDoRegistroTest() {
		RegistroAvaliacao r = new RegistroAvaliacao(8, 30);

		double avaliacaoMedia = r.mediaAvaliacoes();

		assertEquals(avaliacaoMedia, 3.75);
	}
	
	@Test
	void calcularAvaliacaoMediaDoMedicoTest() throws Exception {
		Medico m = new Medico();
		RegistroAvaliacao r = new RegistroAvaliacao(5, 25);
		m.setAvaliacao(r);
		
		double avaliacaoMediaMedico = m.getAvaliacao().mediaAvaliacoes();
		
		assertEquals(avaliacaoMediaMedico, r.mediaAvaliacoes());
	}
}
