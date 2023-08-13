package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;

@SpringBootTest
class InterfaceColecaoMedicoTest {
	@Autowired
	private InterfaceColecaoMedico colecaoMedico;
	
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
	void removerMedicoTest() {
		Medico m = new Medico();
		colecaoMedico.save(m);
		long qtdMedico = colecaoMedico.count();
		
		colecaoMedico.deleteById(m.getId());
		long novaQtdMedico = colecaoMedico.count();
		
		assertEquals(qtdMedico - 1, novaQtdMedico);
	}
		
}
