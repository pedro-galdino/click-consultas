package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Usuario;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ListaVaziaException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.SenhaIncorretaException;

public interface InterfaceServicoUsuario {

	List<Usuario> buscarTodos();

	Usuario buscarPorId(long id) throws ObjetoNaoEncontradoException;

	List<Usuario> buscarPorNome(String nome);

	Usuario buscarPorCpf(String cpf) throws ObjetoNaoEncontradoException;

	Usuario buscarPorEmail(String email) throws ObjetoNaoEncontradoException;

	Usuario salvar(Usuario usuario) throws ObjetoEmUsoException;

	void remover(long id) throws ObjetoNaoEncontradoException;
	
	public Usuario logar(String email, String senha) throws ObjetoNaoEncontradoException, SenhaIncorretaException;

	Carteira buscarCarteiraPorUsuarioId(long usuarioId) throws ObjetoNaoEncontradoException;

	Carteira salvarCarteira(long usuarioId, Carteira carteira) throws ObjetoNaoEncontradoException;

	List<Pix> buscarPixsPorUsuarioId(long usuarioId) throws ObjetoNaoEncontradoException, ListaVaziaException;
	
	Pix buscarPixPorId(long usuarioId, long pixId) throws ObjetoNaoEncontradoException, ListaVaziaException;

	List<Pix> salvarPixCarteira(long usuarioId, Pix pix) throws ObjetoNaoEncontradoException, ObjetoEmUsoException;

	void removerPixCarteira(long usuarioId, long pixId) throws ObjetoNaoEncontradoException, ListaVaziaException;

}