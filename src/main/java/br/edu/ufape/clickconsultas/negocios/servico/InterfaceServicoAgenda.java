package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;
import br.edu.ufape.clickconsultas.negocios.servico.exception.DadosInsuficientesException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoAgenda {

	List<Agenda> buscarTodos();

	Agenda buscarPorId(long id) throws ObjetoNaoEncontradoException;

	Agenda salvar(Agenda agenda) throws DadosInsuficientesException;
	
	List<Agenda> buscarPorIdMedico(long medicoId);

	void remover(long id) throws ObjetoNaoEncontradoException;

}