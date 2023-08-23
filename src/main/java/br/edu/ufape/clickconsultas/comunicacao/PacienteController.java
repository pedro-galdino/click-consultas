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
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.PlanoDeSaude;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {
	@Autowired
	private Fachada fachada;

	@GetMapping()
	public ResponseEntity<List<Paciente>> buscarPacientes() {
		return new ResponseEntity<List<Paciente>>(fachada.buscarPacientes(), HttpStatus.OK);
	}

	@GetMapping("/{pacienteId}")
	public ResponseEntity<?> buscarPacientePorId(@PathVariable Long pacienteId) {
		try {
			return new ResponseEntity<Paciente>(fachada.buscarPacientePorId(pacienteId), HttpStatus.OK);
		} catch (ObjetoNaoEncontradoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<?> cadastrarPaciente(@RequestBody Paciente paciente) {
		try {
			return new ResponseEntity<Paciente>(fachada.salvarPaciente(paciente), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/{pacienteId}")
	public ResponseEntity<?> atualizarPaciente(@RequestBody Paciente paciente, @PathVariable Long pacienteId) {
		try {
			Paciente pacienteAtualizado = fachada.buscarPacientePorId(pacienteId);
			pacienteAtualizado.setNome(paciente.getNome());
			pacienteAtualizado.setDataNascimento(paciente.getDataNascimento());
			pacienteAtualizado.setTelefone(paciente.getTelefone());
			pacienteAtualizado.setSexo(paciente.getSexo());
			pacienteAtualizado.setCidade(paciente.getCidade());
			pacienteAtualizado.setEstado(paciente.getEstado());
			return new ResponseEntity<Paciente>(fachada.salvarPaciente(pacienteAtualizado), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/senha/{pacienteId}")
	public ResponseEntity<?> atualizarSenhaPaciente(@RequestBody String senha, @PathVariable Long pacienteId) {
		try {
			Paciente pacienteAtualizado = fachada.buscarPacientePorId(pacienteId);
			pacienteAtualizado.setSenha(senha);
			return new ResponseEntity<Paciente>(fachada.salvarPaciente(pacienteAtualizado), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{pacienteId}")
	public ResponseEntity<?> removerPaciente(@PathVariable Long pacienteId) {
		try {
			fachada.removerPaciente(pacienteId);
			return new ResponseEntity<String>("Removido com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/plano/{pacienteId}")
	public ResponseEntity<?> buscarPlanoDeSaude(@PathVariable Long pacienteId) {
		try {
			return new ResponseEntity<PlanoDeSaude>(fachada.buscarPlanoDeSaude(pacienteId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/plano/{pacienteId}")
	public ResponseEntity<?> cadastrarPlanoDeSaude(@PathVariable Long pacienteId, @RequestBody PlanoDeSaude plano) {
		try {
			return new ResponseEntity<PlanoDeSaude>(fachada.salvarPlanoDeSaude(pacienteId, plano), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/plano/{pacienteId}")
	public ResponseEntity<?> removerPlanoDeSaude(@PathVariable Long pacienteId) {
		try {
			fachada.removerPlanoDeSaude(pacienteId);
			return new ResponseEntity<String>("Plano removido com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/carteira/{pacienteId}")
	public ResponseEntity<?> buscarCarteiraPorId(@PathVariable Long pacienteId) {
		try {
			return new ResponseEntity<Carteira>(fachada.buscarCarteiraPacientePorId(pacienteId), HttpStatus.OK);
		} catch (ObjetoNaoEncontradoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping("/carteira/{pacienteId}")
	public ResponseEntity<?> atualizarCarteira(@PathVariable Long pacienteId, @RequestBody Carteira carteira) {
		try {
			Carteira carteiraAtualizada = fachada.buscarCarteiraPacientePorId(pacienteId);
			carteiraAtualizada.setSaldo(carteira.getSaldo());
			carteiraAtualizada.setChavesPix(carteira.getChavesPix());
			return new ResponseEntity<Carteira>(fachada.salvarCarteiraPaciente(pacienteId, carteiraAtualizada), HttpStatus.OK);
		} catch (ObjetoNaoEncontradoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}