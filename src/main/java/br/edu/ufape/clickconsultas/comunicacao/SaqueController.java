package br.edu.ufape.clickconsultas.comunicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.clickconsultas.negocios.fachada.Fachada;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Saque;

@RestController
@RequestMapping("/api/saque")
public class SaqueController {
	@Autowired
	private Fachada fachada;

	@GetMapping()
	public ResponseEntity<List<Saque>> buscarSaques() {
		return new ResponseEntity<List<Saque>>(fachada.buscarSaques(), HttpStatus.OK);
	}

	@GetMapping("/{carteiraId}")
	public ResponseEntity<?> buscarSaquesPorCarteiraId(@PathVariable Long carteiraId) {
		try {
			return new ResponseEntity<List<Saque>>(fachada.buscarSaquesPorCarteiraId(carteiraId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{usuarioId}")
	public ResponseEntity<?> cadastrarSaque(@PathVariable Long usuarioId, @RequestBody Saque saque) {
		try {
			return new ResponseEntity<Saque>(fachada.salvarSaque(usuarioId, saque), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{saqueId}")
	public ResponseEntity<?> removerSaque(@PathVariable Long saqueId) {
		try {
			fachada.removerSaque(saqueId);
			return new ResponseEntity<String>("Saque removido com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}