package br.edu.ufape.clickconsultas.negocio.servico.financeiro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoDeposito;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Deposito;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;
import br.edu.ufape.clickconsultas.negocios.servico.financeiro.InterfaceServicoDeposito;

@SpringBootTest
class ServicoDepositoTest {

	@Autowired
	private InterfaceServicoDeposito servicoDeposito;
	
	@Autowired
	private InterfaceColecaoDeposito colecaoDeposito;
	
	@MockBean
	private Date data;
	
	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException {
		Deposito deposito = new Deposito();
		colecaoDeposito.save(deposito);
		
		assertEquals(deposito, servicoDeposito.buscarPorId(deposito.getId()));
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdInexistente() {
		long inexistente = 2342L;
		
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoDeposito.buscarPorId(inexistente));
		
	}
	
	@Test
	@Transactional
	void testarSalvarDeposito() {
		Deposito deposito = new Deposito();
		try {
			servicoDeposito.salvar(deposito);
			return;
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Transactional
	void testarRemoverPorId() throws ObjetoNaoEncontradoException {
		Deposito deposito = new Deposito();
		colecaoDeposito.save(deposito);
		
		List<Deposito> lista = colecaoDeposito.findAll();
		int tamanhoInicial = lista.size();
		
		servicoDeposito.remover(deposito.getId());
		lista = colecaoDeposito.findAll();
		int tamanhoFinal = lista.size();
		
		assertEquals(tamanhoInicial-1, tamanhoFinal);
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdInexistente() throws ObjetoNaoEncontradoException {
		long inexistente = 23234L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoDeposito.remover(inexistente));
	}
	
	@Test
	@Transactional
	void testarBuscarPorDataInexistente() {
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoDeposito.buscarPorData(data));
	}
	
	@Test
	@Transactional
	void testarBuscarPorDataExistente() throws ObjetoNaoEncontradoException {
		Deposito deposito = new Deposito();
		deposito.setData(data);
		colecaoDeposito.save(deposito);
		
		assertTrue(servicoDeposito.buscarPorData(data).contains(deposito));
	}
}
