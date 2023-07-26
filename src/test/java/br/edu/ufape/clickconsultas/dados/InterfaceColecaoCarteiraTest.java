package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoCarteira;
import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoPix;
import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPaciente;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;

@SpringBootTest
class InterfaceColecaoCarteiraTest {
	@Autowired
	private InterfaceColecaoCarteira colecaoCarteira;
	@Autowired
	private InterfaceColecaoPix colecaoPix;
	@Autowired
	private InterfaceColecaoPaciente colecaoPaciente;
	
	@Test
	void cadastrarCarteiraTest() {
		long qtdCarteira = colecaoCarteira.count();
		Paciente p = new Paciente();
		Carteira c = new Carteira(0, null, p);
		
		colecaoPaciente.save(p);
		colecaoCarteira.save(c);
		long novaQtdCarteira = colecaoCarteira.count();
		
		assertEquals(qtdCarteira + 1, novaQtdCarteira);
	}
	
	@Test
	void cadastrarPixTest() {
		long qtdPix = colecaoPix.count();
		Pix p = new Pix("CPF", "999.999.999-99");
		
		colecaoPix.save(p);
		long novaQtdPix = colecaoPix.count();
		
		assertEquals(qtdPix + 1, novaQtdPix);
	}
	
	@Test
	void cadastrarCarteiracomPixTest() {
		long qtdCarteira = colecaoCarteira.count();
		long qtdPix = colecaoPix.count();
		Paciente p = new Paciente();
		Carteira c = new Carteira(0, null, p);
		Pix pix = new Pix("Telefone", "(99)98999-9999");
		List<Pix> lpix = new ArrayList<Pix>();
		lpix.add(pix);
		c.setChavesPix(lpix);
				
		colecaoPaciente.save(p);
		colecaoCarteira.save(c);
		long novaQtdCarteira = colecaoCarteira.count();
		long novaQtdPix = colecaoCarteira.count();
		
		assertEquals(qtdCarteira + 1, novaQtdCarteira);
		assertEquals(qtdPix + 1, novaQtdPix);
	}
	
}
