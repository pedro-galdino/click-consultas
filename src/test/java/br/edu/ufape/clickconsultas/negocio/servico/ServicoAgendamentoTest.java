package br.edu.ufape.clickconsultas.negocio.servico;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoAgendamento;
import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;
import br.edu.ufape.clickconsultas.negocios.modelo.Agendamento;
import br.edu.ufape.clickconsultas.negocios.modelo.HorarioAgendado;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.EnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoAgendamento;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@SpringBootTest
class ServicoAgendamentoTest {
	@Autowired
	private InterfaceServicoAgendamento servicoAgendamento;
	@Autowired
	private InterfaceColecaoAgendamento colecaoAgendamento;
	
	@MockBean
	private EnderecoMedico localConsulta;
	@MockBean
	private HorarioAgendado horarioAgendado;
	@MockBean
	private Paciente paciente;
	@MockBean
	private Agenda agenda;
	
	@Test
	@Transactional
	void buscarPorIdExistente() throws ObjetoNaoEncontradoException {
		Agendamento agendamento = new Agendamento();
		colecaoAgendamento.save(agendamento);

		assertEquals(agendamento, servicoAgendamento.buscarPorId(agendamento.getId()));
		
	}
	
	@Test
	@Transactional
	void buscarPorIdInexistente() throws ObjetoNaoEncontradoException {
		long inexistente = 75455L;

		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoAgendamento.buscarPorId(inexistente));
		
	}
	
	@Test
	@Transactional
	void salvarAgendamentoComDados() {
		Agendamento agendamento = new Agendamento(localConsulta, //localdaConsulta
				"Consulta Geral", //TipodaConsulta
				"Plano A", //PlanoAtendido
				250.0,	//ValorFinaldaConsulta
				"Agendamento com fins de consulta geral, com o plano A, e especificações do Paciente", //Detalhamento
				horarioAgendado, //HorarioAgendado
				paciente,	//Paciente
				agenda	//Agenda
		);
		try {
			servicoAgendamento.salvar(agendamento);
			return;
		} catch (Exception e) {
			fail();
		}
	}
	/*
	@Test
	@Transactional
	void salvarAgendamentoSemDados() throws DadosInsuficientesException {
		Agendamento agendamento = new Agendamento();
		
		assertThrows(DadosInsuficientesException.class, () -> servicoAgendamento.salvar(agendamento));
		
	}
	*/
	@Test
	@Transactional
	void removerPorIdExistente() throws ObjetoNaoEncontradoException {
		Agendamento agendamento = new Agendamento();
		colecaoAgendamento.save(agendamento);
		
		List<Agendamento> lista = colecaoAgendamento.findAll();
		int tamanhoInicial = lista.size();

		servicoAgendamento.remover(agendamento.getId());

		lista = colecaoAgendamento.findAll();
		int tamanhoFinal = lista.size();

		assertEquals(tamanhoFinal, tamanhoInicial - 1);
		
	}
	
	@Test
	@Transactional
	void removerInexistente() throws ObjetoNaoEncontradoException {
		long inexistente = 56947L;
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoAgendamento.remover(inexistente));
	}

}
