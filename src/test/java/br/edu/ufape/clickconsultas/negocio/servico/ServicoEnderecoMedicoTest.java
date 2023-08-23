package br.edu.ufape.clickconsultas.negocio.servico;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoEnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.EnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoEnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@SpringBootTest
class ServicoEnderecoMedicoTest {
	
	@Autowired
	private InterfaceServicoEnderecoMedico servicoEnderecoMedico;
	
	@Autowired
	private InterfaceColecaoEnderecoMedico colecaoEnderecoMedico;
	
	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException {
		EnderecoMedico endereco = new EnderecoMedico();
		colecaoEnderecoMedico.save(endereco);
		
		assertEquals(endereco, servicoEnderecoMedico.buscarPorId(endereco.getId()));
		
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdInexistente() {
		long inexistente = 645634L;
		
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoEnderecoMedico.buscarPorId(inexistente));
		
	}
	
	@Test
	@Transactional
	void testarSalvar() {
		EnderecoMedico endereco = new EnderecoMedico();
		try {
			servicoEnderecoMedico.salvar(endereco);
			return;
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdExistente() throws ObjetoNaoEncontradoException {
		EnderecoMedico endereco = new EnderecoMedico();
		colecaoEnderecoMedico.save(endereco);
		
		List<EnderecoMedico> lista = colecaoEnderecoMedico.findAll();
		int tamanhoInicial = lista.size();
		
		servicoEnderecoMedico.remover(endereco.getId());
		lista = colecaoEnderecoMedico.findAll();
		int tamanhoFinal = lista.size();
		
		assertEquals(tamanhoInicial-1, tamanhoFinal);
		
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdInexistente() {
		long inexistente = 63245L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoEnderecoMedico.remover(inexistente));
	}

}
