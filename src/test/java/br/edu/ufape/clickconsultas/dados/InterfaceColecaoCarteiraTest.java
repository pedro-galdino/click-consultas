package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.negocios.modelo.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.Medico;
import br.edu.ufape.clickconsultas.negocios.modelo.Paciente;
import br.edu.ufape.clickconsultas.negocios.modelo.Pix;

@SpringBootTest
class InterfaceColecaoCarteiraTest {
	@Autowired
	private InterfaceColecaoCarteira colecaoCarteira;
	@Autowired
	private InterfaceColecaoPix colecaoPix;
	
	@Test
	void cadastrarCarteiraTest() {
		long qtdCarteira = colecaoCarteira.count();
		Carteira c = new Carteira(0, null, new Paciente());
		
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
		Carteira c = new Carteira(0, null, new Medico());
		Pix p = new Pix("Telefone", "(99)98999-9999");
		List<Pix> lp = new ArrayList<Pix>();
		lp.add(p);
		c.setChavesPix(lp);
		
		colecaoCarteira.save(c);
		long novaQtdCarteira = colecaoCarteira.count();
		long novaQtdPix = colecaoCarteira.count();
		
		assertEquals(qtdCarteira + 1, novaQtdCarteira);
		assertEquals(qtdPix + 1, novaQtdPix);
	}

}
