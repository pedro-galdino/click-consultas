package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoMedico {

	List<Medico> buscarTodos();

	Medico buscarPorId(long id) throws ObjetoNaoEncontradoException;

	List<Medico> buscarPorNome(String nome);
	
	Medico buscarPorCpf(String cpf) throws ObjetoNaoEncontradoException;

	Medico buscarPorEmail(String email) throws ObjetoNaoEncontradoException;
	
	List<Medico> buscarPorEspecialidade(String nomeEspecialidade) throws ObjetoNaoEncontradoException;
	
	Medico buscarPorCrm(int crm) throws ObjetoNaoEncontradoException;

	Medico salvar(Medico medico) throws ObjetoEmUsoException;

	void remover(long id) throws ObjetoNaoEncontradoException;

}