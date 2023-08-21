package br.edu.ufape.clickconsultas.negocio.servico.financeiro;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoSaque;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Saque;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;
import br.edu.ufape.clickconsultas.negocios.servico.financeiro.InterfaceServicoSaque;

@SpringBootTest
class ServicoSaqueTest {


	@Autowired
	private InterfaceServicoSaque servicoSaque;
	
	@Autowired
	private InterfaceColecaoSaque colecaoSaque;
	
	@MockBean
	private Date data;
	
	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException {
		Saque saque = new Saque();
		colecaoSaque.save(saque);
		
		assertEquals(saque, servicoSaque.buscarPorId(saque.getId()));
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdInexistente() {
		long inexistente = 2342L;
		
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoSaque.buscarPorId(inexistente));
		
	}
	
	@Test
	@Transactional
	void testarSalvarSaque() {
		Saque saque = new Saque();
		try {
			servicoSaque.salvar(saque);
			return;
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Transactional
	void testarRemoverPorId() throws ObjetoNaoEncontradoException {
		Saque saque = new Saque();
		colecaoSaque.save(saque);
		
		List<Saque> lista = colecaoSaque.findAll();
		int tamanhoInicial = lista.size();
		
		servicoSaque.remover(saque.getId());
		lista = colecaoSaque.findAll();
		int tamanhoFinal = lista.size();
		
		assertEquals(tamanhoInicial-1, tamanhoFinal);
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdInexistente() throws ObjetoNaoEncontradoException {
		long inexistente = 23234L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoSaque.remover(inexistente));
	}
	
	@Test
	@Transactional
	void testarBuscarPorDataInexistente() {
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoSaque.buscarPorData(data));
	}
	
	@Test
	@Transactional
	void testarBuscarPorDataExistente() throws ObjetoNaoEncontradoException {
		Saque saque = new Saque();
		saque.setData(data);
		colecaoSaque.save(saque);
		
		assertTrue(servicoSaque.buscarPorData(data).contains(saque));
	}
}
