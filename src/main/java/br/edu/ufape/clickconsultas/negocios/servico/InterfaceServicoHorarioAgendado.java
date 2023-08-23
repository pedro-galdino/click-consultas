package br.edu.ufape.clickconsultas.negocios.servico;

import java.time.LocalDate;
import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.HorarioAgendado;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoHorarioAgendado {

	List<HorarioAgendado> buscarTodos();

	List<HorarioAgendado> BuscarPorData(LocalDate data) throws ObjetoNaoEncontradoException;

	HorarioAgendado buscarPorId(long id) throws ObjetoNaoEncontradoException;

	HorarioAgendado salvar(HorarioAgendado horarioAgendado);

	void remover(long id) throws ObjetoNaoEncontradoException;

}