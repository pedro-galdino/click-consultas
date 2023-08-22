package br.edu.ufape.clickconsultas.negocios.servico;

import java.time.LocalDate;
import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.Horarios;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoHorarios {

	List<Horarios> buscarTodos();

	List<Horarios> buscarPorData(LocalDate data) throws ObjetoNaoEncontradoException;

	Horarios buscarPorId(long id) throws ObjetoNaoEncontradoException;

	Horarios salvar(Horarios horario);

	void remover(long id) throws ObjetoNaoEncontradoException;

}