package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.Date;
import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Deposito;

public interface InterfaceServicoDeposito {

	List<Deposito> buscarTodos();

	List<Deposito> buscarPorData(Date data);

	Deposito buscarPorId(long id);

	Deposito salvar(Deposito deposito);

	void remover(long id);

}