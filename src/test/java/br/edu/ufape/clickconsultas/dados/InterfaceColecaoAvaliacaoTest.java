package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoMedico;
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
	private InterfaceColecaoMedico colecaoMedico;
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
	void cadastrarRegistroAvaliacaoAtravesDeMedicoTest() {
		long qtdRegistroAvaliacao = colecaoRegistroAvaliacao.count();
		Medico m = new Medico();
		RegistroAvaliacao r = new RegistroAvaliacao(7, 35);
		m.setRegistroAvaliacao(r);

		colecaoMedico.save(m);
		long novaQtdRegistroAvaliacao = colecaoRegistroAvaliacao.count();

		assertEquals(qtdRegistroAvaliacao + 1, novaQtdRegistroAvaliacao);
	}
	
	@Test
	void cadastrarAvaliacaoTest() {
		RegistroAvaliacao r = new RegistroAvaliacao();
		colecaoRegistroAvaliacao.save(r);
		long qtdAvaliacao = colecaoAvaliacao.count();
		Paciente p = new Paciente();
		Avaliacao a = new Avaliacao(4.5, "Bom atendimento.", p, r);
		
		colecaoPaciente.save(p);
		colecaoAvaliacao.save(a);
		long novaQtdAvaliacao = colecaoAvaliacao.count();

		assertEquals(qtdAvaliacao + 1, novaQtdAvaliacao);
	}
		
	@Test
	void calcularAvaliacaoMediaDoRegistroTest() {
		RegistroAvaliacao r = new RegistroAvaliacao(8, 30);

		double avaliacaoMedia = r.calcularMediaAvaliacoes();

		assertEquals(avaliacaoMedia, 3.75);
	}
	
	@Test
	void calcularAvaliacaoMediaDoMedicoTest() {
		Medico m = new Medico();
		RegistroAvaliacao r = new RegistroAvaliacao(5, 25);
		m.setRegistroAvaliacao(r);
		
		double avaliacaoMediaMedico = m.getRegistroAvaliacao().calcularMediaAvaliacoes();
		
		assertEquals(avaliacaoMediaMedico, r.calcularMediaAvaliacoes());
	}
	
	@Test
	void calcularAvaliacaoMediaAposAvaliacaoTest() {
		Medico m = new Medico();
		RegistroAvaliacao r = new RegistroAvaliacao(5, 25);
		m.setRegistroAvaliacao(r);
		new Avaliacao(2, null, new Paciente(), r);
		
		double avaliacaoMediaMedico = m.getRegistroAvaliacao().calcularMediaAvaliacoes();
		
		assertEquals(avaliacaoMediaMedico, 4.5);
	}
		
	@Test
	void removerAvaliacaoDoRegistroTest() {
		RegistroAvaliacao r = new RegistroAvaliacao(2, 8);
		Paciente p = new Paciente();
		Avaliacao a = new Avaliacao(4, null, p, r);
		colecaoRegistroAvaliacao.save(r);
		colecaoPaciente.save(p);
		colecaoAvaliacao.save(a);
		long qtdAvaliacao = colecaoAvaliacao.count();
		int numeroAvaliacoesInicial = r.getNumeroAvaliacoes();
		double totalAvaliacoesInicial = r.getTotalAvaliacoes();
		
		a.removerAvaliacao();
		colecaoAvaliacao.deleteById(a.getId());
		colecaoRegistroAvaliacao.save(r);
		long novaQtdAvaliacao = colecaoAvaliacao.count();
		
		assertEquals(qtdAvaliacao - 1, novaQtdAvaliacao);
		assertEquals(numeroAvaliacoesInicial - 1, r.getNumeroAvaliacoes());
		assertEquals(totalAvaliacoesInicial - a.getNota(), r.getTotalAvaliacoes());
	}
}
