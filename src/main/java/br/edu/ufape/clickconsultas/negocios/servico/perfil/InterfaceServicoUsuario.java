package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Usuario;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoUsuario {

	List<Usuario> buscarTodos();

	Usuario buscarPorId(long id) throws ObjetoNaoEncontradoException;

	List<Usuario> buscarPorNome(String nome);

	Usuario buscarPorCpf(String cpf) throws ObjetoNaoEncontradoException;

	Usuario buscarPorEmail(String email) throws ObjetoNaoEncontradoException;

	Usuario salvar(Usuario usuario) throws ObjetoEmUsoException;

	void remover(long id) throws ObjetoNaoEncontradoException;

	Carteira buscarCarteiraPorUsuarioId(long usuarioId) throws ObjetoNaoEncontradoException;

	Carteira salvarCarteira(long usuarioId, Carteira carteira) throws ObjetoNaoEncontradoException;

	Pix buscarPixPorId(long usuarioId, long pixId) throws ObjetoNaoEncontradoException;

	List<Pix> salvarPixCarteira(long usuarioId, Pix pix) throws ObjetoNaoEncontradoException;

	void removerPixCarteira(long usuarioId, long pixId) throws ObjetoNaoEncontradoException;

}