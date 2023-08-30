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
import br.edu.ufape.clickconsultas.negocios.modelo.Consulta;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {
	@Autowired
	private Fachada fachada;

	@GetMapping()
	public ResponseEntity<?> listarConsultas() {
		try {
			return new ResponseEntity<List<Consulta>>(fachada.buscarConsultas(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{consultaId}")
	public ResponseEntity<?> listarConsultaPorId(@PathVariable long consultaId) {
		try {
			return new ResponseEntity<List<Consulta>>(fachada.buscarConsultas(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/paciente/{pacienteId}")
	public ResponseEntity<?> listarConsultasPorPacienteId(@PathVariable long pacienteId) {
		try {
			return new ResponseEntity<List<Consulta>>(fachada.buscarConsultaPorPacienteId(pacienteId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/medico/{medicoId}")
	public ResponseEntity<?> listarConsultasPorMedicoId(@PathVariable long medicoId) {
		try {
			return new ResponseEntity<List<Consulta>>(fachada.buscarConsultaPorMedicoId(medicoId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<?> cadastrarConsulta(@RequestBody Consulta consulta) {
		try {
			return new ResponseEntity<Consulta>(fachada.salvarConsulta(consulta), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{consultaId}")
	public ResponseEntity<?> removerConsulta(@PathVariable long consultaId) {
		try {
			fachada.removerConsulta(consultaId);
			return new ResponseEntity<String>("Consulta removida com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
