package br.edu.ufape.clickconsultas.negocio.servico;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoAvaliacao;
import br.edu.ufape.clickconsultas.negocios.modelo.Avaliacao;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoAvaliacao;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@SpringBootTest
class ServicoAvaliacaoTest {
	
	@Autowired
	private InterfaceServicoAvaliacao servicoAvaliacao;
	
	@Autowired
	private InterfaceColecaoAvaliacao colecaoAvaliacao;
	
	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException {
		Avaliacao avaliacao = new Avaliacao();
		colecaoAvaliacao.save(avaliacao);
		
		assertEquals(avaliacao, servicoAvaliacao.buscarPorId(avaliacao.getId()));
		
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdInexistente() {
		long inexistente = 134836L;
		
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoAvaliacao.buscarPorId(inexistente));
		
	}
	
	@Test
	@Transactional
	void testarSalvarAvaliacao() {
		Avaliacao avaliacao = new Avaliacao();
		try {
			servicoAvaliacao.salvar(avaliacao);
			return;
		} catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdExistente() throws ObjetoNaoEncontradoException {
		Avaliacao avaliacao = new Avaliacao();
		colecaoAvaliacao.save(avaliacao);
		
		List<Avaliacao> lista = colecaoAvaliacao.findAll();
		int tamanhoInicial = lista.size();
		
		servicoAvaliacao.remover(avaliacao.getId());
		lista = colecaoAvaliacao.findAll();
		int tamanhoFinal = lista.size();
		
		assertEquals(tamanhoInicial-1, tamanhoFinal);
		
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdInexistente() throws ObjetoNaoEncontradoException {
		long inexistente = 465695L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoAvaliacao.remover(inexistente));
	}
	
}
