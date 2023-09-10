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
import br.edu.ufape.clickconsultas.negocios.modelo.Agendamento;
import br.edu.ufape.clickconsultas.negocios.modelo.HorarioAgendado;

@RestController
@RequestMapping("/api/agendamento")
public class AgendamentoController {
	@Autowired
	private Fachada fachada;

	@GetMapping()
	public ResponseEntity<?> listarAgendamentos() {
		try {
			return new ResponseEntity<List<Agendamento>>(fachada.buscarAgendamentos(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{agendamentoId}")
	public ResponseEntity<?> listarAgendamentoPorId(@PathVariable long agendamentoId) {
		try {
			return new ResponseEntity<Agendamento>(fachada.buscarAgendamentoPorId(agendamentoId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{agendamentoId}/horario")
	public ResponseEntity<?> listarHoraDoAgendamento(@PathVariable long agendamentoId) {
		try {
			return new ResponseEntity<HorarioAgendado>(fachada.buscarHorarioAgendadoPorAgendamentoId(agendamentoId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/paciente/{pacienteId}")
	public ResponseEntity<?> listarAgendamentoPorPacienteId(@PathVariable long pacienteId) {
		try {
			return new ResponseEntity<List<Agendamento>>(fachada.buscarAgendamentoPorPacienteId(pacienteId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> cadastrarAgendamento(@RequestBody Agendamento agendamento) {
		try {
			return new ResponseEntity<Agendamento>(fachada.salvarAgendamento(agendamento), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/planos/{pacienteId}/{agendaId}")
	public ResponseEntity<?> buscarPlanosDeSaudeDisponiveisParaAgendamento(@PathVariable long pacienteId, @PathVariable long agendaId) {
		try {
			return new ResponseEntity<List<String>>(fachada.buscarPlanosDeSaudeDisponiveisParaAgendamento(pacienteId, agendaId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping("/{agendamentoId}")
	public ResponseEntity<?> atualizarAgendamento(@PathVariable long agendamentoId, @RequestBody Agendamento agendamento) {
		try {
			Agendamento agendamentoAtualizado = fachada.buscarAgendamentoPorId(agendamentoId);
			//atualizar
			
			return new ResponseEntity<Agendamento>(fachada.salvarAgendamento(agendamentoAtualizado), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{agendamentoId}")
	public ResponseEntity<?> removerAgendamento(@PathVariable long agendamentoId) {
		try {
			fachada.removerAgendamento(agendamentoId);
			return new ResponseEntity<String>("Agendamento removido com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
