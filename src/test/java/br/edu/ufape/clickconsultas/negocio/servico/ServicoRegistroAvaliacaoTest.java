package br.edu.ufape.clickconsultas.negocio.servico;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoRegistroAvaliacao;
import br.edu.ufape.clickconsultas.negocios.modelo.RegistroAvaliacao;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoRegistroAvaliacao;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@SpringBootTest
class ServicoRegistroAvaliacaoTest {

	@Autowired
	private InterfaceServicoRegistroAvaliacao servicoRegistroAvaliacao;
	
	@Autowired
	private InterfaceColecaoRegistroAvaliacao colecaoRegistro;
	
	
	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException {
		RegistroAvaliacao registro = new RegistroAvaliacao();
		colecaoRegistro.save(registro);
		
		assertEquals(registro, servicoRegistroAvaliacao.buscarPorId(registro.getId()));
		
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdInexistente() {
		long inexistente = 56896L;
		
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoRegistroAvaliacao.buscarPorId(inexistente));
		
	}
	
	@Test
	@Transactional
	void testarSalvar() {
		RegistroAvaliacao registro = new RegistroAvaliacao();
		try {
			servicoRegistroAvaliacao.salvar(registro);
			return;
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdExistente() throws ObjetoNaoEncontradoException {
		RegistroAvaliacao registro = new RegistroAvaliacao();
		colecaoRegistro.save(registro);
		
		List<RegistroAvaliacao> lista = colecaoRegistro.findAll();
		int tamanhoInicial = lista.size();
		
		servicoRegistroAvaliacao.remover(registro.getId());
		lista = colecaoRegistro.findAll();
		int tamanhoFinal = lista.size();
		
		assertEquals(tamanhoInicial-1, tamanhoFinal);
		
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdInexistente() {
		long inexistente = 652475L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoRegistroAvaliacao.remover(inexistente));
		
	}
}
