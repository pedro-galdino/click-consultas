package br.edu.ufape.clickconsultas.comunicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.clickconsultas.negocios.fachada.Fachada;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;

@RestController
public class UsuarioController {
	@Autowired
	private Fachada fachada;

	@RequestMapping("/")
	String hello() {
		return "Hello world";
	}

	@PostMapping("cadastrar")
	public ResponseEntity<?> cadastrarUsuario(@RequestBody Medico novoMedico) {
		try {
			Medico medicoCadastrado = fachada.salvarMedico(novoMedico);
			return new ResponseEntity<>(medicoCadastrado, HttpStatus.CREATED);
		} catch (Exception e) {
			// Tratar exceção caso ocorra algum erro no cadastro
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}