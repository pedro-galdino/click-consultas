package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoCRM;
import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoEspecialidade;
import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Especialidade;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;

@SpringBootTest
class InterfaceColecaoMedicoTest {
	@Autowired
	private InterfaceColecaoMedico colecaoMedico;
	@Autowired
	private InterfaceColecaoCRM colecaoCRM;
	@Autowired
	private InterfaceColecaoEspecialidade colecaoEspecialidade;
	
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
	void removerCrmDeMedicoTest() {
		Medico m = new Medico();
		CRM c = new CRM("PE", 6450);
		List<CRM> lc = new ArrayList<CRM>();
		lc.add(c);
		m.setCrm(lc);
		colecaoMedico.save(m);
		long qtdCrm = colecaoCRM.count();
		
		m.removerCrm(c);
		colecaoMedico.save(m);
		colecaoCRM.deleteById(c.getId());
		long novaQtdCrm = colecaoCRM.count();
		
		assertEquals(qtdCrm - 1, novaQtdCrm);
	}
	
	@Test
	void removerEspecialidadeDeMedicoTest() {
		Medico m = new Medico();
		Especialidade e = new Especialidade("Pediatra", 12354);
		List<Especialidade> le = new ArrayList<Especialidade>();
		le.add(e);
		m.setEspecialidades(le);
		colecaoMedico.save(m);
		long qtdEspecialidade = colecaoEspecialidade.count();
		
		m.removerEspecialidade(e);
		colecaoMedico.save(m);
		colecaoEspecialidade.deleteById(e.getId());
		long novaQtdEspecialidade = colecaoEspecialidade.count();
		
		assertEquals(qtdEspecialidade - 1, novaQtdEspecialidade);
	}
		
}
