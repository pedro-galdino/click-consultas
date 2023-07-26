package br.edu.ufape.clickconsultas.comunicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.clickconsultas.negocios.fachada.UsuarioFachada;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Usuario;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioFachada usuarioFachada;

	@RequestMapping("/")
	String hello() {
		return "Hello world";
	}

	/*@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario novoUsuario) {
		try {
			Usuario usuarioCadastrado = usuarioFachada.cadastrarMedico(novoUsuario.getNome(), novoUsuario.getEmail(), novoUsuario.getSenha());
			return new ResponseEntity<>(usuarioCadastrado, HttpStatus.CREATED);
		} catch (Exception e) {
			// Tratar exceção caso ocorra algum erro no cadastro
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
}