package br.edu.ufape.clickconsultas.negocio.servico.financeiro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoCarteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;
import br.edu.ufape.clickconsultas.negocios.servico.financeiro.InterfaceServicoCarteira;

@SpringBootTest
class ServicoCarteiraTest {

	@Autowired
	private InterfaceServicoCarteira servicoCarteira;
	
	@Autowired
	private InterfaceColecaoCarteira colecaoCarteira;
	
	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException {
		Carteira carteira = new Carteira();
		colecaoCarteira.save(carteira);
		
		assertEquals(carteira, servicoCarteira.buscarPorId(carteira.getId()));
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdInexistente() {
		long inexistente = 2342L;
		
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoCarteira.buscarPorId(inexistente));
		
	}
	
	@Test
	@Transactional
	void testarSalvarCarteira() {
		Carteira carteira = new Carteira();
		try {
			servicoCarteira.salvar(carteira);
			return;
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	@Transactional
	void testarRemoverPorId() throws ObjetoNaoEncontradoException {
		Carteira carteira = new Carteira();
		colecaoCarteira.save(carteira);
		
		List<Carteira> lista = colecaoCarteira.findAll();
		int tamanhoInicial = lista.size();
		
		servicoCarteira.remover(carteira.getId());
		lista = colecaoCarteira.findAll();
		int tamanhoFinal = lista.size();
		
		assertEquals(tamanhoInicial-1, tamanhoFinal);
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdInexistente() throws ObjetoNaoEncontradoException {
		long inexistente = 23234L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoCarteira.remover(inexistente));
	}

}
