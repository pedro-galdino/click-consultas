package br.edu.ufape.clickconsultas.negocio.servico;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoHorarioAgendado;
import br.edu.ufape.clickconsultas.negocios.modelo.HorarioAgendado;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoHorarioAgendado;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@SpringBootTest
class ServicoHorarioAgendadoTest {
	
	@Autowired
	private InterfaceServicoHorarioAgendado servicoHorarioAgendado;
	
	@Autowired
	private InterfaceColecaoHorarioAgendado colecaoHorarioAgendado;
	
	@MockBean
	private LocalDate data;

	@Test
	@Transactional
	void testarBuscarPorDataExistente() throws ObjetoNaoEncontradoException {
		HorarioAgendado horario = new HorarioAgendado();
		horario.setData(data);
		colecaoHorarioAgendado.save(horario);
		
		assertTrue(servicoHorarioAgendado.buscarPorData(data).contains(horario));
	}
	
	@Test
	@Transactional
	void testarBuscarPorDataInexistente() {
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoHorarioAgendado.buscarPorData(data));
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException {
		HorarioAgendado horario = new HorarioAgendado();
		colecaoHorarioAgendado.save(horario);
		
		assertEquals(horario, servicoHorarioAgendado.buscarPorId(horario.getId()));
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdInexistente() {
		long inexistente = 5677L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoHorarioAgendado.buscarPorId(inexistente));
	}
	
	@Test
	@Transactional
	void testarSalvarHorario() {
		HorarioAgendado horario = new HorarioAgendado();
		try {
			colecaoHorarioAgendado.save(horario);
			return;
		} catch (Exception e){
			fail();
		}
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdExistente() throws ObjetoNaoEncontradoException {
		HorarioAgendado horario = new HorarioAgendado();
		colecaoHorarioAgendado.save(horario);
		
		List<HorarioAgendado> lista = colecaoHorarioAgendado.findAll();
		int tamanhoInicial = lista.size();
		
		servicoHorarioAgendado.remover(horario.getId());
		lista = colecaoHorarioAgendado.findAll();
		int tamanhoFinal = lista.size();
		
		assertEquals(tamanhoInicial-1, tamanhoFinal);
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdInexistente() {
		long inexistente = 8594L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoHorarioAgendado.remover(inexistente));
	}

}
