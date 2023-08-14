package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;

public interface InterfaceServicoAgenda {

	List<Agenda> buscarTodos();

	Agenda buscarPorId(long id);

	Agenda salvar(Agenda agenda);

	void remover(long id);

}