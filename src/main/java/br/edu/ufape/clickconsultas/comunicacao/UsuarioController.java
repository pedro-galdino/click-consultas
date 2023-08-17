package br.edu.ufape.clickconsultas.comunicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.clickconsultas.negocios.fachada.Fachada;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioInexistenteException;

@RestController
public class UsuarioController {
	@Autowired
	private Fachada fachada;

	@RequestMapping("/")
	String hello() {
		return "Hello world";
	}

	@PostMapping("cadastrar")
	public ResponseEntity<?> cadastrarMedico(@RequestBody Medico novoMedico) {
		try {
			Medico medicoCadastrado = fachada.salvarMedico(novoMedico);
			return new ResponseEntity<>(medicoCadastrado, HttpStatus.CREATED);
		} catch (Exception e) {
			// Tratar exceção caso ocorra algum erro no cadastro
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	@DeleteMapping("remover/{id}")
	public ResponseEntity<String> removerMedico(@PathVariable long id) {
		try {
			fachada.removerMedico(id);
			return new ResponseEntity<>( HttpStatus.ACCEPTED);
		} catch (UsuarioInexistenteException e) {
			return new ResponseEntity<>("Usuario não encontrado", HttpStatus.NOT_FOUND);
		}
		
	}
	*/
}