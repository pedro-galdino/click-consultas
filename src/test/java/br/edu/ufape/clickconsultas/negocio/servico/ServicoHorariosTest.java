package br.edu.ufape.clickconsultas.negocio.servico;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoHorarios;
import br.edu.ufape.clickconsultas.negocios.modelo.Horarios;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoHorarios;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@SpringBootTest
class ServicoHorariosTest {
	
	@Autowired
	private InterfaceServicoHorarios servicoHorarios;
	@Autowired
	private InterfaceColecaoHorarios colecaoHorarios;
	
	@MockBean
	private LocalDate data;
	
	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException {
		Horarios h = new Horarios();
		colecaoHorarios.save(h);
		
		assertEquals(h, servicoHorarios.buscarPorId(h.getId()));
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdInexistente() throws ObjetoNaoEncontradoException {
		long id = 234L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoHorarios.buscarPorId(id));
	}
	
	@Test
	@Transactional
	void testarBuscarPorDataExistente() throws ObjetoNaoEncontradoException {
		Horarios h = new Horarios();
		h.setData(data);
		colecaoHorarios.save(h);
		assertTrue(servicoHorarios.buscarPorData(data).contains(h));
	}
	
	@Test
	@Transactional 
	void testarBuscarPorDataInexistente() throws ObjetoNaoEncontradoException {
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoHorarios.buscarPorData(data));
	}
	
	@Test
	@Transactional
	void testarSalvarHorario() {
		Horarios h = new Horarios();
		try {
			servicoHorarios.salvar(h);
			return;
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdExistente() throws ObjetoNaoEncontradoException {
		Horarios h = new Horarios();
		colecaoHorarios.save(h);
		
		int tamanhoInicial = colecaoHorarios.findAll().size();
		servicoHorarios.remover(h.getId());
		int tamanhoFinal = colecaoHorarios.findAll().size();
		
		assertEquals(tamanhoInicial-1, tamanhoFinal);
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdInexistente() throws ObjetoNaoEncontradoException {
		long inexistente = 234L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoHorarios.remover(inexistente));
	}
}
