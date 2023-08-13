package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.Date;
import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Saque;

public interface InterfaceServicoSaque {

	List<Saque> buscarTodos();

	List<Saque> buscarPorData(Date data);

	Saque buscarPorId(long id);

	Saque salvar(Saque saque);

	void remover(long id);

}