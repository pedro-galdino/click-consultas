package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.negocios.modelo.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.Deposito;
import br.edu.ufape.clickconsultas.negocios.modelo.Pix;
import br.edu.ufape.clickconsultas.negocios.modelo.Saque;

@SpringBootTest
class InterfaceColecaoTransacaoTest {
	@Autowired
	private InterfaceColecaoSaque colecaoSaque;
	@Autowired
	private InterfaceColecaoDeposito colecaoDeposito;
	
	@Test
	void cadastrarSaqueTest() {
		long qtdSaque = colecaoSaque.count();
		Pix p = new Pix("E-mail", "email@yahoo.com");
		Saque s = new Saque(25, new Date(), "Caixa", new Carteira(), p);

		colecaoSaque.save(s);
		long novaQtdSaque = colecaoSaque.count();

		assertEquals(qtdSaque + 1, novaQtdSaque);
	}

	@Test
	void processarSaqueTest() throws Exception {
		Carteira c = new Carteira(500, null, null);
		Saque s = new Saque(100, new Date(), "Bradesco", c, null);
		float saldoCarteira = c.getSaldo();

		s.processarTransacao();
		colecaoSaque.save(s);

		assertEquals(saldoCarteira - s.getValor(), c.getSaldo());
	}

	@Test
	void processarSaqueInvalidoTest() {
		Carteira c = new Carteira(100, null, null);
		Saque s = new Saque(-100, new Date(), "Itaú", c, null);
		float saldoCarteira = c.getSaldo();
		String msgErro = null;

		try {
			s.processarTransacao();
			colecaoSaque.save(s);
		} catch (Exception e) {
			msgErro = e.getMessage();
		}

		assertEquals("Valor para saque inválido.", msgErro);
		assertEquals(saldoCarteira, c.getSaldo());
	}

	@Test
	void processarSaqueMaiorQueSaldoTest() {
		Carteira c = new Carteira(200, null, null);
		Saque s = new Saque(300, new Date(), "Bradesco", c, null);
		float saldoCarteira = c.getSaldo();
		String msgErro = null;

		try {
			s.processarTransacao();
			colecaoSaque.save(s);
		} catch (Exception e) {
			msgErro = e.getMessage();
		}

		assertEquals("Saldo insuficiente para realizar o saque.", msgErro);
		assertEquals(saldoCarteira, c.getSaldo());
	}

	@Test
	void cadastrarDepositoTest() {
		long qtdDeposito = colecaoDeposito.count();
		Deposito d = new Deposito(50, new Date(), "pix gerado", new Carteira());

		colecaoDeposito.save(d);
		long novaQtdDeposito = colecaoDeposito.count();

		assertEquals(qtdDeposito + 1, novaQtdDeposito);
	}

	@Test
	void processarDepositoTest() throws Exception {
		Carteira c = new Carteira(200, null, null);
		Deposito d = new Deposito(80, new Date(), "pix gerado", c);
		float saldoCarteira = c.getSaldo();

		d.processarTransacao();
		colecaoDeposito.save(d);

		assertEquals(saldoCarteira + d.getValor(), c.getSaldo());
	}

	@Test
	void processarDepositoInvalidoTest() {
		Carteira c = new Carteira(100, null, null);
		Deposito d = new Deposito(-200, new Date(), "pix gerado", c);
		float saldoCarteira = c.getSaldo();
		String msgErro = null;

		try {
			d.processarTransacao();
			colecaoDeposito.save(d);
		} catch (Exception e) {
			msgErro = e.getMessage();
		}

		assertEquals("Valor para depósito inválido.", msgErro);
		assertEquals(saldoCarteira, c.getSaldo());
	}

}
