package br.edu.ufape.clickconsultas.negocio.servico.perfil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPaciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.PlanoDeSaude;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;
import br.edu.ufape.clickconsultas.negocios.servico.perfil.InterfaceServicoPaciente;

@SpringBootTest
class ServicoPacienteTest {
	
	@Autowired
	private InterfaceServicoPaciente servicoPaciente;
	
	@Autowired
	private InterfaceColecaoPaciente colecaoPaciente;
	
	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException {
		Paciente paciente = new Paciente();
		colecaoPaciente.save(paciente);
		
		assertEquals(paciente, servicoPaciente.buscarPorId(paciente.getId()));
	}

	@Test
	@Transactional
	void testarBuscarPorIdInexistente() {
		long id = 243523L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoPaciente.buscarPorId(id));
	}

	@Test
	@Transactional
	void testarSalvarPaciente() throws ObjetoEmUsoException {
		String email = "carlos@gmail.com";
		String email2 = "eduardo@gmail.com";
		Paciente p1 = new Paciente();
		Paciente p2 = new Paciente();
		p1.setEmail(email);
		p1.setCpf("111222333-56");
		p2.setEmail(email2);
		p2.setCpf("999888777-45");
		
		servicoPaciente.salvar(p1);
		servicoPaciente.salvar(p2);
		
		assertEquals(p1, colecaoPaciente.findById(p1.getId()).orElse(null));
		assertEquals(p2, colecaoPaciente.findById(p2.getId()).orElse(null));
		
	}

	@Test
	@Transactional
	void testarBuscarPlanoDeSaude() throws ObjetoNaoEncontradoException {
		Paciente p = new Paciente();
		PlanoDeSaude planoDeSaude = new PlanoDeSaude(12345, "Unimed");
		p.setPlano(planoDeSaude);
		
		colecaoPaciente.save(p);
		
		assertEquals(planoDeSaude, servicoPaciente.buscarPlanoDeSaude(p.getId()));
	}
	
	@Test
	@Transactional
	void testarBuscarPlanoDeSaudeInexistente() {
		Paciente p = new Paciente();
		colecaoPaciente.save(p);
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoPaciente.buscarPlanoDeSaude(p.getId()));
	}
	
	@Test
	@Transactional
	void testarSalvarPlanoDeSaude() throws ObjetoNaoEncontradoException {
		Paciente p = new Paciente();
		PlanoDeSaude planoDeSaude = new PlanoDeSaude(1234, "Amil");
		
		colecaoPaciente.save(p);
		
		assertTrue(p.getPlano() == null);
		
		servicoPaciente.salvarPlanoDeSaude(p.getId(), planoDeSaude);
		
		assertEquals(planoDeSaude.getNumero(), p.getPlano().getNumero());
		assertEquals(planoDeSaude.getOperadora(), p.getPlano().getOperadora());
	}
	
	@Test
	@Transactional
	void testarRemoverPlanoDeSaude() throws ObjetoNaoEncontradoException {
		Paciente p = new Paciente();
		PlanoDeSaude planoDeSaude = new PlanoDeSaude(1234, "Amil");
		colecaoPaciente.save(p);
		
		assertTrue(p.getPlano() == null);
		
		p.setPlano(planoDeSaude);
		
		assertTrue(p.getPlano() != null);
		
		servicoPaciente.removerPlanoDeSaude(p.getId());
		
		assertTrue(p.getPlano() == null);
	}
	
	@Test
	@Transactional
	void testarRemoverPlanoDeSaudeInexistente() {
		Paciente p = new Paciente();
		colecaoPaciente.save(p);
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoPaciente.removerPlanoDeSaude(p.getId()));
	}
	
	
	
}
