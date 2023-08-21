package br.edu.ufape.clickconsultas.negocio.servico.financeiro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoPix;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;
import br.edu.ufape.clickconsultas.negocios.servico.financeiro.InterfaceServicoPix;

@SpringBootTest
class ServicoPixTest {

	@Autowired
	private InterfaceServicoPix servicoPix;
	
	@Autowired
	private InterfaceColecaoPix colecaoPix;
	
	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException {
		Pix p = new Pix();
		colecaoPix.save(p);
		
		assertEquals(p, servicoPix.buscarPorId(p.getId()));
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdInexistente() {
		long id = 234L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoPix.buscarPorId(id));
	}
	
	@Test
	@Transactional
	void testarSalvar() {
		Pix p = new Pix();
		Pix p2 = new Pix();
		
		try {
			servicoPix.salvar(p);
			servicoPix.salvar(p2);
			return;
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	@Transactional
	void testarRemoverPorId() throws ObjetoNaoEncontradoException {
		Pix p = new Pix();
		colecaoPix.save(p);
		
		int tamanhoInicial = colecaoPix.findAll().size();
		servicoPix.remover(p.getId());
		int tamanhoFInal = colecaoPix.findAll().size();
		
		assertEquals(tamanhoInicial-1, tamanhoFInal);
		
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdInexistente() {
		long id = 234L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoPix.remover(id));
	}
	
	
}
