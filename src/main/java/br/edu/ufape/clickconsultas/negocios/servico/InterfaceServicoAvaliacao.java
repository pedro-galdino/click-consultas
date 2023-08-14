package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.Avaliacao;

public interface InterfaceServicoAvaliacao {

	List<Avaliacao> buscarTodos();

	Avaliacao buscarPorId(long id);

	Avaliacao salvar(Avaliacao avaliacao);

	void remover(long id);

}