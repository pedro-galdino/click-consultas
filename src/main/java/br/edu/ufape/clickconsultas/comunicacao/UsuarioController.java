package br.edu.ufape.clickconsultas.comunicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.clickconsultas.negocios.fachada.UsuarioFachada;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Usuario;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioFachada usuarioFachada;

	@RequestMapping("/")
	String hello() {
		return "Hello world";
	}

	@PostMapping("cadastrar")
	public ResponseEntity<?> cadastrarUsuario(@RequestBody Medico novoUsuario) {
		try {
			Medico usuarioCadastrado = usuarioFachada.cadastrarMedico(novoUsuario.getNome(), novoUsuario.getEmail(), novoUsuario.getSenha());
			return new ResponseEntity<>(usuarioCadastrado, HttpStatus.CREATED);
		} catch (Exception e) {
			// Tratar exceção caso ocorra algum erro no cadastro
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}