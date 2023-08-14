package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.Agendamento;

public interface InterfaceServicoAgendamento {

	List<Agendamento> buscarTodos();

	Agendamento buscarPorId(long id);

	Agendamento salvar(Agendamento agendamento);

	void remover(long id);

}