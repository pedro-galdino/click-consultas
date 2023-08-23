package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.EnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoEnderecoMedico {

	List<EnderecoMedico> buscarTodos();

	EnderecoMedico buscarPorId(long id) throws ObjetoNaoEncontradoException;

	EnderecoMedico salvar(EnderecoMedico enderecoMedico);

	void remover(long id) throws ObjetoNaoEncontradoException;

}