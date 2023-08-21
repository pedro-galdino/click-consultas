package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.Date;
import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Saque;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoSaque {

	List<Saque> buscarTodos();

	List<Saque> buscarPorData(Date data) throws ObjetoNaoEncontradoException;

	Saque buscarPorId(long id) throws ObjetoNaoEncontradoException;

	Saque salvar(Saque saque);

	void remover(long id) throws ObjetoNaoEncontradoException;

}