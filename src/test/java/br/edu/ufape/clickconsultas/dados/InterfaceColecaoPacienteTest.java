package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPaciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;

@SpringBootTest
class InterfaceColecaoPacienteTest {
	@Autowired
	private InterfaceColecaoPaciente colecaoPaciente;
			
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
	void removerPacienteTest() {
		Paciente p = new Paciente();
		colecaoPaciente.save(p);
		long qtdPaciente = colecaoPaciente.count();
		
		colecaoPaciente.deleteById(p.getId());
		long novaQtdPaciente = colecaoPaciente.count();
		
		assertEquals(qtdPaciente - 1, novaQtdPaciente);
	}
			
}
