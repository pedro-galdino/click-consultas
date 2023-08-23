package br.edu.ufape.clickconsultas.negocio.servico;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoConsulta;
import br.edu.ufape.clickconsultas.negocios.modelo.Consulta;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoConsulta;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@SpringBootTest
class ServicoConsultaTest {
	
	@Autowired
	private InterfaceServicoConsulta servicoConsulta;
	
	@Autowired
	private InterfaceColecaoConsulta colecaoConsulta;
	
	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException {
		Consulta consulta = new Consulta();
		colecaoConsulta.save(consulta);
		
		assertEquals(consulta, servicoConsulta.buscarPorId(consulta.getId()));
		
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdInexistente() {
		long inexistente = 33461L;
		
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoConsulta.buscarPorId(inexistente));
	}
	
	@Test
	@Transactional
	void testarSalvarConsulta() {
		Consulta consulta = new Consulta();
		try {
			servicoConsulta.salvar(consulta);
			return;
		} catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdExistente() throws ObjetoNaoEncontradoException {
		Consulta consulta = new Consulta();
		colecaoConsulta.save(consulta);
		
		List<Consulta> lista = colecaoConsulta.findAll();
		int tamanhoInicial = lista.size();
		
		servicoConsulta.remover(consulta.getId());
		lista = colecaoConsulta.findAll();
		
		int tamanhoFinal = lista.size();
		
		assertEquals(tamanhoInicial-1, tamanhoFinal);
		
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdInexistente() {
		long inexistente = 214756L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoConsulta.remover(inexistente));
		
	}

}
