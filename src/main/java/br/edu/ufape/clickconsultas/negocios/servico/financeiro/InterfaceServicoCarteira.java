package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoCarteira {

	List<Carteira> buscarTodos();

	Carteira buscarPorId(long id) throws ObjetoNaoEncontradoException;

	Carteira salvar(Carteira carteira);

	void remover(long id) throws ObjetoNaoEncontradoException;

}