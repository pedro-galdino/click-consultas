package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;

public interface InterfaceServicoCarteira {

	List<Carteira> buscarTodos();

	Carteira buscarPorId(long id);

	Carteira salvar(Carteira carteira);

	void remover(long id);

}