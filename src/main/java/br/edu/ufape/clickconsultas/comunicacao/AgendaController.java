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
import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;
import br.edu.ufape.clickconsultas.negocios.modelo.Horarios;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController {
	@Autowired
	private Fachada fachada;

	@GetMapping()
	public ResponseEntity<?> listarAgendas() {
		try {
			return new ResponseEntity<List<Agenda>>(fachada.buscarAgendas(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{agendaId}")
	public ResponseEntity<?> listarAgendaPorId(@PathVariable long agendaId) {
		try {
			return new ResponseEntity<Agenda>(fachada.buscarAgendaPorId(agendaId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/medico/{medicoId}")
	public ResponseEntity<?> listarAgendasPorMedicoId(@PathVariable long medicoId) {
		try {
			return new ResponseEntity<List<Agenda>>(fachada.buscarAgendasPorMedicoId(medicoId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{medicoId}")
	public ResponseEntity<?> salvarAgenda(@RequestBody Agenda agenda, @PathVariable long medicoId) {
		try {
			return new ResponseEntity<Agenda>(fachada.salvarAgenda(agenda, medicoId), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/{agendaId}")
	public ResponseEntity<?> editarAgenda(@RequestBody Agenda agenda, @PathVariable long agendaId) {
		try {
			Agenda agendaExistente = fachada.buscarAgendaPorId(agendaId);
			agendaExistente.setEspecialidadeMedica(agenda.getEspecialidadeMedica());
			agendaExistente.setTiposConsulta(agenda.getTiposConsulta());
			agendaExistente.setPlanosAtendidos(agenda.getPlanosAtendidos());
			agendaExistente.setValorConsulta(agenda.getValorConsulta());
			agendaExistente.setContato(agenda.getContato());
			agendaExistente.setHorariosDisponiveis(agenda.getHorariosDisponiveis());
			agendaExistente.setHorariosAgendados(agenda.getHorariosAgendados());
			return new ResponseEntity<Agenda>(fachada.salvarAgenda(agendaExistente, agendaId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{agendaId}")
	public ResponseEntity<?> removerAgenda(@PathVariable long agendaId) {
		try {
			fachada.removerAgenda(agendaId);
			return new ResponseEntity<String>("Agenda removida com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{agendaId}/horarios")
	public ResponseEntity<?> listarHorariosPorAgendaId(@PathVariable long agendaId) {
		try {
			return new ResponseEntity<List<Horarios>>(fachada.buscarHorariosPorAgendaId(agendaId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/{agendaId}/horarios")
	public ResponseEntity<?> salvarHorarios(@RequestBody Horarios horario, @PathVariable long agendaId) {
		try {
			return new ResponseEntity<Agenda>(fachada.salvarHorarios(agendaId, horario), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{agendaId}/horarios")
	public ResponseEntity<?> removerHorarios(@PathVariable long agendaId, @RequestBody Horarios horario) {
		try {
			fachada.removerHorarios(agendaId, horario);
			return new ResponseEntity<String>("Horário removido com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping("/{agendaId}/horarios/{horarioId}")
	public ResponseEntity<?> editarHorario(@PathVariable long agendaId, @PathVariable long horarioId, @RequestBody Horarios novoHorario) {
		try {
			return new ResponseEntity<Agenda>(fachada.editarHorarios(agendaId, horarioId, novoHorario), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}