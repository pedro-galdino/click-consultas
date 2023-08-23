package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.RegistroAvaliacao;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoRegistroAvaliacao {

	List<RegistroAvaliacao> buscarTodos();

	RegistroAvaliacao buscarPorId(long id) throws ObjetoNaoEncontradoException;

	RegistroAvaliacao salvar(RegistroAvaliacao registroAvaliacao);

	void remover(long id) throws ObjetoNaoEncontradoException;

}