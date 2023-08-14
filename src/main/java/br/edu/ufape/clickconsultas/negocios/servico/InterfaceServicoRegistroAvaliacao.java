package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.RegistroAvaliacao;

public interface InterfaceServicoRegistroAvaliacao {

	List<RegistroAvaliacao> buscarTodos();

	RegistroAvaliacao buscarPorId(long id);

	RegistroAvaliacao salvar(RegistroAvaliacao registroAvaliacao);

	void remover(long id);

}