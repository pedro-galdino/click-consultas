package br.edu.ufape.clickconsultas.negocio.servico;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoAgenda;
import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;
import br.edu.ufape.clickconsultas.negocios.modelo.EnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.HorarioAgendado;
import br.edu.ufape.clickconsultas.negocios.modelo.Horarios;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoAgenda;
import br.edu.ufape.clickconsultas.negocios.servico.exception.DadosInsuficientesException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@SpringBootTest
class ServicoAgendaTest {
	@Autowired
	private InterfaceServicoAgenda servicoAgenda;
	@Autowired
	private InterfaceColecaoAgenda colecaoAgenda;

	@MockBean
	private EnderecoMedico localConsulta;
	@MockBean
	private Horarios horariosDisponiveis;
	@MockBean
	private HorarioAgendado horariosAgendados;
	@MockBean
	private Medico medico;

	@Test
	@Transactional
	void criarAgenda() throws DadosInsuficientesException {
		Agenda agenda = new Agenda("Cardiologia", // especialidadeMedica
				List.of("Consulta Geral", "Exame de Rotina"), // tiposConsulta
				List.of("Plano A", "Plano B"), // planosAtendidos
				150.0, // valorConsulta
				"contato@medico.com", // contato
				List.of(horariosDisponiveis), // horariosDisponiveis
				List.of(horariosAgendados), // horariosAgendados
				medico // medico
		);
		try {
			servicoAgenda.salvar(agenda);
			return;
		} catch (DadosInsuficientesException e) {
			fail();
		}
	}

	@Test
	@Transactional
	void criarAgendaSemDados() throws DadosInsuficientesException {
		Agenda agenda = new Agenda();

		assertThrows(DadosInsuficientesException.class, () -> servicoAgenda.salvar(agenda));

	}

	@Test
	@Transactional
	void buscarPorIdExistente() throws ObjetoNaoEncontradoException {
		Agenda agenda = new Agenda();
		colecaoAgenda.save(agenda);

		assertEquals(agenda, servicoAgenda.buscarPorId(agenda.getId()));
	}

	@Test
	@Transactional
	void buscarPorIdInexistente() throws ObjetoNaoEncontradoException {
		long inexistente = 2L;

		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoAgenda.buscarPorId(inexistente));
	}

	@Test
	@Transactional
	void removerExistente() throws ObjetoNaoEncontradoException {
		Agenda agenda = new Agenda();
		colecaoAgenda.save(agenda);
		List<Agenda> lista = colecaoAgenda.findAll();
		int tamanhoInicial = lista.size();

		servicoAgenda.remover(agenda.getId());

		lista = colecaoAgenda.findAll();
		int tamanhoFinal = lista.size();

		assertEquals(tamanhoFinal, tamanhoInicial - 1);

	}

	@Test
	@Transactional
	void removerInexistente() throws ObjetoNaoEncontradoException {
		long inexistente = 2L;
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoAgenda.remover(inexistente));
	}

}