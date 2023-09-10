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
import br.edu.ufape.clickconsultas.negocios.modelo.Avaliacao;

@RestController
@RequestMapping("/api/avaliacao")
public class AvaliacaoController {
	@Autowired
	private Fachada fachada;

	@GetMapping()
	public ResponseEntity<?> listarAvaliacoes() {
		try {
			return new ResponseEntity<List<Avaliacao>>(fachada.buscarAvaliacoes(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/paciente/{pacienteId}")
	public ResponseEntity<?> buscarAvaliacoesPorPacienteId(@PathVariable long pacienteId) {
		try {
			return new ResponseEntity<List<Avaliacao>>(fachada.buscarAvaliacoesPorPacienteId(pacienteId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{avaliacaoId}")
	public ResponseEntity<?> listarAvaliacaoPorId(@PathVariable long avaliacaoId) {
		try {
			return new ResponseEntity<Avaliacao>(fachada.buscarAvaliacaoPorId(avaliacaoId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/registro/{registroId}")
	public ResponseEntity<?> listarAvaliacaoPorRegistroId(@PathVariable long registroId) {
		try {
			return new ResponseEntity<List<Avaliacao>>(fachada.buscarAvaliacoesPorRegistroId(registroId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/consulta/{consultaId}")
	public ResponseEntity<?> buscarAvaliacaoPorConsultaId(@PathVariable long consultaId) {
		try {
			return new ResponseEntity<Avaliacao>(fachada.buscarAvaliacaoPorConsultaId(consultaId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{idPaciente}/{idMedico}")
	public ResponseEntity<?> cadastrarAvaliacao(@RequestBody Avaliacao avaliacao, @PathVariable  long idPaciente,  @PathVariable  long idMedico) {
		try {
			return new ResponseEntity<Avaliacao>(fachada.salvarAvaliacao(avaliacao, idPaciente, idMedico), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{avaliacaoId}")
	public ResponseEntity<?> removerAvaliacao(@PathVariable long avaliacaoId) {
		try {
			fachada.removerAvaliacao(avaliacaoId);
			return new ResponseEntity<String>("Avaliação removida com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
