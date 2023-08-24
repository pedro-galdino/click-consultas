package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoCarteira;
import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoDeposito;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Deposito;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;

@SpringBootTest
public class InterfaceColecaoDepositoTest {

	@Autowired
	private InterfaceColecaoDeposito colecaoDeposito;
	@Autowired
	private InterfaceColecaoCarteira colecaoCarteira;
	private List<Pix> chavesPix;
	
	@Test
	void cadastrarCarteiraTest() {
		long qtdCarteira = colecaoCarteira.count();
		Carteira c = new Carteira(0, chavesPix);
		Deposito d = new Deposito(80.0, new Date(), "12345678", c);
		
		colecaoCarteira.save(d);
		long novaQtdCarteira = colecaoCarteira.count();
		
		assertEquals(qtdCarteira + 1, novaQtdCarteira);
	}
	
	@Test
	void cadastrarDepositoTest() {
		double valorDeposito = 15.4;
	    Date dataDeposito = new Date();
	    String chavePix = "chave-pix-exemplo";
		
		Carteira c = new Carteira(0, chavesPix);
	    Deposito d = new Deposito(15.4, dataDeposito, "minhachave@minha", c);
		
	    colecaoCarteira.save(d);
	    colecaoDeposito.save(d);
	    
	    long novaQtdDeposito = colecaoDeposito.count();
	    long qtdDeposito = colecaoDeposito.count();

	    assertEquals(qtdDeposito + 1, novaQtdDeposito);
	}


}
