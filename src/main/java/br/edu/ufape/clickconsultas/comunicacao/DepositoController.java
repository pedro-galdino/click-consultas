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
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Deposito;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@RestController
@RequestMapping("/api/deposito")
public class DepositoController {

	@Autowired
	private Fachada fachada;

	@GetMapping()
	public ResponseEntity<List<Deposito>> buscarDepositos() {
		return new ResponseEntity<List<Deposito>>(fachada.buscarDepositos(), HttpStatus.OK);
	}
	
	@GetMapping("/{carteiraId}")
	public ResponseEntity<?> buscarDepositosPorCarteiraId(@PathVariable Long carteiraId) {
		try {
			return new ResponseEntity<List<Deposito>>(fachada.buscarDepositosPorCarteiraId(carteiraId), HttpStatus.OK);
		} catch (ObjetoNaoEncontradoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/{usuarioId}/usuario")
	public ResponseEntity<?> cadastrarDeposito(@PathVariable Long usuarioId, @RequestBody Deposito deposito) {
		try {
			return new ResponseEntity<Deposito>(fachada.salvarDeposito(usuarioId, deposito), HttpStatus.OK);
		} catch (ObjetoNaoEncontradoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{depositoId}/")
	public ResponseEntity<?> removerDeposito(@PathVariable Long depositoId) {
		try {
			fachada.removerDeposito(depositoId);
			return new ResponseEntity<String>("Depósito removido com sucesso.", HttpStatus.OK);
		} catch (ObjetoNaoEncontradoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
