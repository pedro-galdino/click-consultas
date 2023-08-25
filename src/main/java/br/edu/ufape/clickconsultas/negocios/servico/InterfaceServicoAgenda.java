package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoAgenda {

	List<Agenda> buscarTodos();

	Agenda buscarPorId(long id) throws ObjetoNaoEncontradoException;

	Agenda salvar(Agenda agenda);
	
	List<Agenda> buscarPorIdMedico(long medicoId) throws ObjetoNaoEncontradoException;

	void remover(long id) throws ObjetoNaoEncontradoException;

}