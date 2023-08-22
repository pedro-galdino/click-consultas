package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoCarteira;
import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoPix;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;

@SpringBootTest
class InterfaceColecaoCarteiraTest {
	@Autowired
	private InterfaceColecaoCarteira colecaoCarteira;
	@Autowired
	private InterfaceColecaoPix colecaoPix;
	
	@Test
	void cadastrarCarteiraTest() {
		long qtdCarteira = colecaoCarteira.count();
		Carteira c = new Carteira(0, null);
		
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
		Carteira c = new Carteira(0, null);
		Pix pix = new Pix("Telefone", "(99)98999-9991");
		List<Pix> lpix = new ArrayList<Pix>();
		lpix.add(pix);
		c.setChavesPix(lpix);
				
		colecaoCarteira.save(c);
		long novaQtdCarteira = colecaoCarteira.count();
		long novaQtdPix = colecaoPix.count();
		
		assertEquals(qtdCarteira + 1, novaQtdCarteira);
		assertEquals(qtdPix + 1, novaQtdPix);
	}
	
	@Test
	void removerPixDeCarteiraTest() {
		Carteira c = new Carteira(250, null);
		Pix p = new Pix("Telefone", "(99)98199-1234");
		List<Pix> lp = new ArrayList<Pix>();
		lp.add(p);
		c.setChavesPix(lp);	
		colecaoCarteira.save(c);
		long qtdPix = colecaoPix.count();
		
		c.removerPix(p);
		colecaoCarteira.save(c);
		colecaoPix.deleteById(p.getId());
		long novaQtdPix = colecaoPix.count();
		
		assertEquals(qtdPix - 1, novaQtdPix);
		
	}
	
}
