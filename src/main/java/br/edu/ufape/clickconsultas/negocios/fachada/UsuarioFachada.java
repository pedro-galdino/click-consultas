package br.edu.ufape.clickconsultas.negocios.fachada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoUsuario;
import br.edu.ufape.clickconsultas.negocios.modelo.Usuario;

@Component
public class UsuarioFachada {
	@Autowired
	private InterfaceColecaoUsuario colecaoUsuario;
	
	public Usuario cadastrarUsuario(String nome, String email, String senha) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);
        return colecaoUsuario.save(novoUsuario);
    }
}
