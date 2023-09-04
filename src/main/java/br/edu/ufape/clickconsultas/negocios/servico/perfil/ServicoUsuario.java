package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoUsuario;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Usuario;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ListaVaziaException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.SenhaIncorretaException;

@Service
@Component
public class ServicoUsuario implements InterfaceServicoUsuario {
	@Autowired
	private InterfaceColecaoUsuario colecaoUsuario;

	public List<Usuario> buscarTodos() {
		return colecaoUsuario.findAll();
	}

	public Usuario buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Usuario u = colecaoUsuario.findById(id).orElse(null);
		if (u == null)
			throw new ObjetoNaoEncontradoException("o", "usuario");
		return u;
	}

	public List<Usuario> buscarPorNome(String nome) {
		return colecaoUsuario.findByNomeIgnoreCase(nome.trim());
	}

	public Usuario buscarPorCpf(String cpf) throws ObjetoNaoEncontradoException {
		Usuario u = colecaoUsuario.findByCpf(cpf);
		if (u == null)
			throw new ObjetoNaoEncontradoException("o", "usuario");
		return u;
	}

	public Usuario buscarPorEmail(String email) throws ObjetoNaoEncontradoException {
		Usuario u = colecaoUsuario.findByEmailIgnoreCase(email.trim());
		if (u == null)
			throw new ObjetoNaoEncontradoException("o", "email");
		return u;
	}

	public Usuario salvar(Usuario usuario) throws ObjetoEmUsoException {
		// Verifica se o email já está em uso
		Usuario usuarioExistenteByEmail = colecaoUsuario.findByEmailIgnoreCase(usuario.getEmail());
		if (usuarioExistenteByEmail != null && usuario.getId() != usuarioExistenteByEmail.getId())
			throw new ObjetoEmUsoException("o", "e-mail");

		// Verifica se o cpf já está em uso
		Usuario usuarioExistenteByCpf = colecaoUsuario.findByCpf(usuario.getCpf());
		if (usuarioExistenteByCpf != null && usuario.getId() != usuarioExistenteByCpf.getId())
			throw new ObjetoEmUsoException("o", "CPF");

		return colecaoUsuario.save(usuario);
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Usuario u = buscarPorId(id);
		colecaoUsuario.deleteById(u.getId());
	}

	public Usuario logar(String email, String senha) throws ObjetoNaoEncontradoException, SenhaIncorretaException {
		Usuario u = buscarPorEmail(email);
		String senhaUsuario = u.getSenha();
		if (!senhaUsuario.equals(senha))
			throw new SenhaIncorretaException();
		return u;
	}

	public Carteira buscarCarteiraPorUsuarioId(long usuarioId) throws ObjetoNaoEncontradoException {
		return buscarPorId(usuarioId).getCarteira();
	}

	public Carteira salvarCarteira(long usuarioId, Carteira carteira) throws ObjetoNaoEncontradoException {
		Usuario u = buscarPorId(usuarioId);
		u.setCarteira(carteira);
		colecaoUsuario.save(u);
		return u.getCarteira();
	}

	public List<Pix> buscarPixsPorUsuarioId(long usuarioId) throws ObjetoNaoEncontradoException, ListaVaziaException {
		Carteira c = buscarCarteiraPorUsuarioId(usuarioId);
		if (c.getChavesPix() == null || c.getChavesPix().isEmpty())
			throw new ListaVaziaException("pix");

		return c.getChavesPix();
	}

	public Pix buscarPixPorId(long usuarioId, long pixId) throws ObjetoNaoEncontradoException, ListaVaziaException {
		Carteira c = buscarCarteiraPorUsuarioId(usuarioId);
		if (c.getChavesPix() == null || c.getChavesPix().isEmpty())
			throw new ListaVaziaException("pix");

		for (Pix pix : c.getChavesPix())
			if (pix.getId() == pixId)
				return pix;

		throw new ObjetoNaoEncontradoException("o", "pix");
	}

	public List<Pix> salvarPixCarteira(long usuarioId, Pix pix) throws ObjetoNaoEncontradoException, ObjetoEmUsoException {
		Carteira c = buscarCarteiraPorUsuarioId(usuarioId);
		for (Pix p : c.getChavesPix())
			if (p.getChave().equals(pix.getChave()))
				throw new ObjetoEmUsoException("o", "pix");

		c.adicionarChavePix(pix);
		salvarCarteira(usuarioId, c);
		return c.getChavesPix();
	}

	public void removerPixCarteira(long usuarioId, long pixId) throws ObjetoNaoEncontradoException, ListaVaziaException {
		Carteira c = buscarPorId(usuarioId).getCarteira();
		Pix pix = buscarPixPorId(usuarioId, pixId);
		c.removerChavePix(pix);
		salvarCarteira(usuarioId, c);
	}

}
