package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.Consulta;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ListaVaziaException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoConsulta {

	List<Consulta> buscarTodos();

	Consulta buscarPorId(long id) throws ObjetoNaoEncontradoException;

	public List<Consulta> buscarPorPacienteId(long pacienteId) throws ObjetoNaoEncontradoException, ListaVaziaException;

	public List<Consulta> buscarPorMedicoId(long medicoId) throws ObjetoNaoEncontradoException, ListaVaziaException;

	Consulta buscarPorAgendamentoId(long agendamentoId);

	Consulta salvar(Consulta consulta);

	void remover(long id) throws ObjetoNaoEncontradoException;

}