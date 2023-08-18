package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.Agendamento;
import br.edu.ufape.clickconsultas.negocios.servico.exception.DadosInsuficientesException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoAgendamento {

	List<Agendamento> buscarTodos();

	Agendamento buscarPorId(long id) throws ObjetoNaoEncontradoException;

	Agendamento salvar(Agendamento agendamento) throws DadosInsuficientesException;

	void remover(long id) throws ObjetoNaoEncontradoException;

}