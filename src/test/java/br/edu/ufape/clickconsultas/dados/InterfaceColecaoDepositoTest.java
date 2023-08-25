package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoDeposito;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Deposito;

@SpringBootTest
public class InterfaceColecaoDepositoTest {
	@Autowired
	private InterfaceColecaoDeposito colecaoDeposito;

	@Test
	void cadastrarDepositoTest() {
		long qtdDeposito = colecaoDeposito.count();
		double valorDeposito = 15.4;
		Date dataDeposito = new Date();
		String chavePix = "chave-pix-exemplo";
		Deposito d = new Deposito(valorDeposito, dataDeposito, chavePix, null);

		colecaoDeposito.save(d);
		long novaQtdDeposito = colecaoDeposito.count();

		assertEquals(qtdDeposito + 1, novaQtdDeposito);
	}

}
