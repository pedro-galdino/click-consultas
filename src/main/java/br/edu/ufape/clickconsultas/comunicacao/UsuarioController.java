package br.edu.ufape.clickconsultas.comunicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.clickconsultas.negocios.fachada.Fachada;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Usuario;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	@Autowired
	private Fachada fachada;

	@GetMapping()
	public ResponseEntity<List<Usuario>> buscarUsuarios() {
		return new ResponseEntity<List<Usuario>>(fachada.buscarUsuarios(), HttpStatus.OK);
	}

	@GetMapping("/{usuarioId}")
	public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long usuarioId) {
		try {
			return new ResponseEntity<Usuario>(fachada.buscarUsuarioPorId(usuarioId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping("/email/{usuarioEmail}/senha")
	public ResponseEntity<?> atualizarSenhaUsuarioEmail(@RequestBody String senha, @PathVariable String usuarioEmail) {
		try {
			Usuario usuarioAtualizado = fachada.buscarUsuarioPorEmail(usuarioEmail);
			usuarioAtualizado.setSenha(senha);
			return new ResponseEntity<Usuario>(fachada.salvarUsuario(usuarioAtualizado), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/{usuarioId}/senha")
	public ResponseEntity<?> atualizarSenhaUsuario(@RequestBody String senha, @PathVariable Long usuarioId) {
		try {
			Usuario usuarioAtualizado = fachada.buscarUsuarioPorId(usuarioId);
			usuarioAtualizado.setSenha(senha);
			return new ResponseEntity<Usuario>(fachada.salvarUsuario(usuarioAtualizado), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<?> removerUsuario(@PathVariable Long usuarioId) {
		try {
			fachada.removerUsuario(usuarioId);
			return new ResponseEntity<String>("Usuário removido com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{usuarioId}/carteira")
	public ResponseEntity<?> buscarCarteiraPorUsuarioId(@PathVariable Long usuarioId) {
		try {
			return new ResponseEntity<Carteira>(fachada.buscarCarteiraPorUsuarioId(usuarioId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/{usuarioId}/carteira")
	public ResponseEntity<?> atualizarSaldoCarteira(@PathVariable Long usuarioId, @RequestBody double saldo) {
		try {
			Carteira carteiraAtualizada = fachada.buscarCarteiraPorUsuarioId(usuarioId);
			carteiraAtualizada.setSaldo(saldo);
			return new ResponseEntity<Carteira>(fachada.salvarCarteira(usuarioId, carteiraAtualizada), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{usuarioId}/pix")
	public ResponseEntity<?> buscarPixsCarteiraPorUsuarioId(@PathVariable Long usuarioId) {
		try {
			return new ResponseEntity<List<Pix>>(fachada.buscarPixsCarteiraPorUsuarioId(usuarioId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{usuarioId}/pix/{pixId}")
	public ResponseEntity<?> buscarPixCarteiraPorId(@PathVariable Long usuarioId, @PathVariable Long pixId) {
		try {
			return new ResponseEntity<Pix>(fachada.buscarPixCarteiraPorId(usuarioId, pixId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{usuarioId}/pix")
	public ResponseEntity<?> cadastrarPixCarteira(@PathVariable Long usuarioId, @RequestBody Pix pix) {
		try {
			return new ResponseEntity<List<Pix>>(fachada.salvarPixCarteira(usuarioId, pix), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{usuarioId}/pix/{pixId}")
	public ResponseEntity<?> removerPixCarteira(@PathVariable Long usuarioId, @PathVariable Long pixId) {
		try {
			fachada.removerPixCarteira(usuarioId, pixId);
			return new ResponseEntity<String>("Pix removido com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
