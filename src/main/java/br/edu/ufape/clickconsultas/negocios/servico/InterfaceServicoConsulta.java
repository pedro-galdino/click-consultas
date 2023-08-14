package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.Consulta;

public interface InterfaceServicoConsulta {

	List<Consulta> buscarTodos();

	Consulta buscarPorId(long id);

	Consulta salvar(Consulta consulta);

	void remover(long id);

}