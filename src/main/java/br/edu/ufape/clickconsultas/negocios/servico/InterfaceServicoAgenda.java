package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;
import br.edu.ufape.clickconsultas.negocios.servico.exception.AgendaInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.DadosInsuficientesException;

public interface InterfaceServicoAgenda {

	List<Agenda> buscarTodos();

	Agenda buscarPorId(long id)  throws AgendaInexistenteException ;

	Agenda salvar(Agenda agenda) throws DadosInsuficientesException;

	void remover(long id) throws AgendaInexistenteException;

}