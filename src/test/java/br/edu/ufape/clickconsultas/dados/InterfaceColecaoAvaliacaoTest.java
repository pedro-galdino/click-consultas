package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPaciente;
import br.edu.ufape.clickconsultas.negocios.modelo.Avaliacao;
import br.edu.ufape.clickconsultas.negocios.modelo.RegistroAvaliacao;
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
	void cadastrarRegistroAvaliacaoTest() {
		long qtdRegistroAvaliacao = colecaoRegistroAvaliacao.count();
		RegistroAvaliacao r = new RegistroAvaliacao(4, 20);

		colecaoRegistroAvaliacao.save(r);
		long novaQtdRegistroAvaliacao = colecaoRegistroAvaliacao.count();

		assertEquals(qtdRegistroAvaliacao + 1, novaQtdRegistroAvaliacao);
	}
	
	@Test
	void cadastrarAvaliacaoTest() {
		RegistroAvaliacao r = new RegistroAvaliacao();
		colecaoRegistroAvaliacao.save(r);
		long qtdAvaliacao = colecaoAvaliacao.count();
		Paciente p = new Paciente();
		long idConsulta = 1L;
		Avaliacao a = new Avaliacao(4.5, "Bom atendimento.", p, r, idConsulta);
		
		colecaoPaciente.save(p);
		colecaoAvaliacao.save(a);
		long novaQtdAvaliacao = colecaoAvaliacao.count();

		assertEquals(qtdAvaliacao + 1, novaQtdAvaliacao);
	}
	
}
