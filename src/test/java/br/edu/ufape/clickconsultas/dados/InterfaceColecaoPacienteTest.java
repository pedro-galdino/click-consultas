package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPaciente;
import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPlanoDeSaude;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.PlanoDeSaude;

@SpringBootTest
class InterfaceColecaoPacienteTest {
	@Autowired
	private InterfaceColecaoPaciente colecaoPaciente;
	@Autowired
	private InterfaceColecaoPlanoDeSaude colecaoPlano;
			
	@Test
	void cadastrarPacienteTest() {
		long qtdPaciente = colecaoPaciente.count();
		LocalDate dataNasc = LocalDate.parse("2000-12-30");
		Paciente p = new Paciente("Maria", "999.999.999-92", dataNasc, "Mulher", "99999-9992", "email@email.com", "1234", "Garanhuns", "PE", null);
		
		colecaoPaciente.save(p);
		long novaQtdPaciente = colecaoPaciente.count();
		
		assertEquals(qtdPaciente + 1, novaQtdPaciente);
	}
	
	@Test
	void cadastrarPlanoDeSaudeTest() {
		long qtdPlano = colecaoPlano.count();
		PlanoDeSaude p = new PlanoDeSaude(99999999, "Unimed");
		
		colecaoPlano.save(p);
		long novaQtdPlano = colecaoPlano.count();
		
		assertEquals(qtdPlano + 1, novaQtdPlano);
	}
	
	@Test
	void cadastrarPacienteComPlanoTest() {
		long qtdPlano = colecaoPlano.count();
		long qtdPaciente = colecaoPaciente.count();
		Paciente p = new Paciente("José", "999.999.999-93", LocalDate.now(), "Homem", "99999-9993", "email@email.com", "1234", "Garanhuns", "PE", null);
		PlanoDeSaude plano = new PlanoDeSaude(99887766, "Bradesco Saúde");
		p.setPlano(plano);
		
		colecaoPaciente.save(p);
		long novaQtdPlano = colecaoPlano.count();
		long novaQtdPaciente = colecaoPaciente.count();
		
		assertEquals(qtdPaciente + 1, novaQtdPaciente);
		assertEquals(qtdPlano + 1, novaQtdPlano);
	}
	
	@Test
	void removerPlanoDePacienteTest() {
		Paciente p = new Paciente();
		PlanoDeSaude plano = new PlanoDeSaude(99887766, "Bradesco Saúde");
		p.setPlano(plano);
		colecaoPaciente.save(p);
		long qtdPlano = colecaoPlano.count();
		
		colecaoPlano.deleteById(plano.getId());
		long novaQtdPlano = colecaoPlano.count();
		
		assertEquals(qtdPlano - 1, novaQtdPlano);
	}
			
}
