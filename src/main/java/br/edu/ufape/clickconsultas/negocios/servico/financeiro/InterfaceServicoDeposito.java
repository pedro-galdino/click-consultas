package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.Date;
import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Deposito;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoDeposito {

	List<Deposito> buscarTodos();

	List<Deposito> buscarPorData(Date data) throws ObjetoNaoEncontradoException;

	Deposito buscarPorId(long id) throws ObjetoNaoEncontradoException;
	
	List<Deposito> buscarPorCarteiraId(long carteiraId) throws ObjetoNaoEncontradoException;

	Deposito salvar(Deposito deposito);

	void remover(long id) throws ObjetoNaoEncontradoException;

}