package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.EnderecoMedico;

public interface InterfaceServicoEnderecoMedico {

	List<EnderecoMedico> buscarTodos();

	EnderecoMedico buscarPorId(long id);

	EnderecoMedico salvar(EnderecoMedico enderecoMedico);

	void remover(long id);

}