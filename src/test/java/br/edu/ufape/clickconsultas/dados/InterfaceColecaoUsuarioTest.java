package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoCRM;
import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoEspecialidade;
import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoMedico;
import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPaciente;
import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPlanoDeSaude;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Especialidade;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.PlanoDeSaude;

@SpringBootTest
class InterfaceColecaoUsuarioTest {
	@Autowired
	private InterfaceColecaoMedico colecaoMedico;
	@Autowired
	private InterfaceColecaoCRM colecaoCRM;
	@Autowired
	private InterfaceColecaoEspecialidade colecaoEspecialidade;
	@Autowired
	private InterfaceColecaoPaciente colecaoPaciente;
	@Autowired
	private InterfaceColecaoPlanoDeSaude colecaoPlano;
	
	@Test
	void cadastrarMedicoTest() {
		long qtdMedico = colecaoMedico.count();
		LocalDate dataNasc = LocalDate.of(1990, 12, 30);
		Medico m = new Medico("João", "999.999.999-91", dataNasc, "Homem", "99999-9991", "email@email.com", "1234", null, null, null, null);
		
		colecaoMedico.save(m);
		long novaQtdMedico = colecaoMedico.count();
		
		assertEquals(qtdMedico + 1, novaQtdMedico);
	}
	
	@Test
	void cadastrarCRMTest() {
		long qtdCRM = colecaoCRM.count();
		CRM c = new CRM("PE", 9999);
		
		colecaoCRM.save(c);
		long novaQtdCRM = colecaoCRM.count();
		
		assertEquals(qtdCRM + 1, novaQtdCRM);
	}
	
	@Test
	void cadastrarMedicoComCRMTest() {
		long qtdCRM = colecaoCRM.count();
		long qtdMedico = colecaoMedico.count();
		Medico m = new Medico("Thiago", "999.999.999-92", LocalDate.now(), "Homem", "99999-9922", "email@email.com", "1234", null, null, null, null);
		CRM c = new CRM("PE", 2222);
		List<CRM> lc = new ArrayList<CRM>();
		lc.add(c);
		m.setCrm(lc);
		
		colecaoMedico.save(m);
		long novaQtdCRM = colecaoCRM.count();
		long novaQtdMedico = colecaoMedico.count();
		
		assertEquals(qtdMedico + 1, novaQtdMedico);
		assertEquals(qtdCRM + 1, novaQtdCRM);
	}
	
	@Test
	void cadastrarEspecialidadeTest() {
		long qtdEspecialidade = colecaoEspecialidade.count();
		Especialidade e = new Especialidade("Cardiologista", 99999);
		
		colecaoEspecialidade.save(e);
		long novaQtdEspecialidade = colecaoEspecialidade.count();
		
		assertEquals(qtdEspecialidade + 1, novaQtdEspecialidade);
	}
	
	@Test
	void cadastrarMedicoComEspecialidadeTest() {
		long qtdEspecialidade = colecaoEspecialidade.count();
		long qtdMedico = colecaoMedico.count();
		Medico m = new Medico("Edna", "999.999.999-92", LocalDate.now(), "Mulher", "99999-9921", "email@email.com", "1234", null, null, null, null);
		Especialidade e = new Especialidade("Pediatra", 11111);
		List<Especialidade> le = new ArrayList<Especialidade>();
		le.add(e);
		m.setEspecialidades(le);
		
		colecaoMedico.save(m);
		long novaQtdEspecialidade = colecaoEspecialidade.count();
		long novaQtdMedico = colecaoMedico.count();
		
		assertEquals(qtdMedico + 1, novaQtdMedico);
		assertEquals(qtdEspecialidade + 1, novaQtdEspecialidade);
	}
		
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
			
}
