package br.edu.ufape.clickconsultas.comunicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.clickconsultas.negocios.fachada.Fachada;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@RestController
@RequestMapping("/api/carteira")
public class CarteiraController {
	@Autowired
	private Fachada fachada;

	@GetMapping()
	public ResponseEntity<List<Carteira>> buscarCarteiras() {
		return new ResponseEntity<List<Carteira>>(fachada.buscarCarteiras(), HttpStatus.OK);
	}

	@GetMapping("/{carteiraId}")
	public ResponseEntity<?> buscarCarteiraPorId(@PathVariable Long carteiraId) {
		try {
			return new ResponseEntity<Carteira>(fachada.buscarCarteiraPorId(carteiraId), HttpStatus.OK);
		} catch (ObjetoNaoEncontradoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/{carteiraId}")
	public ResponseEntity<?> atualizarCarteira(@PathVariable Long carteiraId, @RequestBody Carteira carteira) {
		try {
			Carteira carteiraAtualizada = fachada.buscarCarteiraPorId(carteiraId);
			carteiraAtualizada.setSaldo(carteira.getSaldo());
			carteiraAtualizada.setChavesPix(carteira.getChavesPix());
			return new ResponseEntity<Carteira>(fachada.salvarCarteira(carteiraAtualizada), HttpStatus.OK);
		} catch (ObjetoNaoEncontradoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
