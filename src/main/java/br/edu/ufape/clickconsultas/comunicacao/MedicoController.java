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
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Especialidade;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@RestController
@RequestMapping("/api/medico")
public class MedicoController {
	@Autowired
	private Fachada fachada;

	@GetMapping("/{medicoId}")
	public ResponseEntity<?> buscarMedicoPorId(@PathVariable Long medicoId) {
		try {
			return new ResponseEntity<Medico>(fachada.buscarMedicoPorId(medicoId), HttpStatus.OK);
		} catch (ObjetoNaoEncontradoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> cadastrarMedico(@RequestBody Medico medico) {
		try {
			return new ResponseEntity<Medico>(fachada.cadastrarMedico(medico), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping("/{medicoId}")
	public ResponseEntity<?> atualizarMedico(@RequestBody Medico medico, @PathVariable Long medicoId) {
		try {
			Medico medicoAtualizado = fachada.buscarMedicoPorId(medicoId);
			medicoAtualizado.setNome(medico.getNome());
			medicoAtualizado.setDataNascimento(medico.getDataNascimento());
			medicoAtualizado.setTelefone(medico.getTelefone());
			medicoAtualizado.setSexo(medico.getSexo());
			return new ResponseEntity<Medico>(fachada.salvarMedico(medicoAtualizado), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{medicoId}/crm")
	public ResponseEntity<?> buscarCrms(@PathVariable Long medicoId) {
		try {
			return new ResponseEntity<List<CRM>>(fachada.buscarCrms(medicoId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{medicoId}/crm/{crmId}")
	public ResponseEntity<?> buscarCrmPorId(@PathVariable Long medicoId, @PathVariable Long crmId) {
		try {
			return new ResponseEntity<CRM>(fachada.buscarCrmPorId(medicoId, crmId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{medicoId}/crm")
	public ResponseEntity<?> cadastrarCrm(@PathVariable Long medicoId, @RequestBody CRM crm) {
		try {
			return new ResponseEntity<List<CRM>>(fachada.salvarCrm(medicoId, crm), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{medicoId}/crm/{crmId}")
	public ResponseEntity<?> removerCrm(@PathVariable Long medicoId, @PathVariable Long crmId) {
		try {
			fachada.removerCrm(medicoId, crmId);
			return new ResponseEntity<String>("CRM removido com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{medicoId}/especialidade")
	public ResponseEntity<?> buscarEspecialidades(@PathVariable Long medicoId) {
		try {
			return new ResponseEntity<List<Especialidade>>(fachada.buscarEspecialidades(medicoId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{medicoId}/especialidade/{especialidadeId}")
	public ResponseEntity<?> buscarEspecialidadePorId(@PathVariable Long medicoId, @PathVariable Long especialidadeId) {
		try {
			return new ResponseEntity<Especialidade>(fachada.buscarEspecialidadePorId(medicoId, especialidadeId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/{medicoId}/especialidade")
	public ResponseEntity<?> cadastrarEspecialidade(@PathVariable Long medicoId, @RequestBody Especialidade e) {
		try {
			return new ResponseEntity<List<Especialidade>>(fachada.salvarEspecialidade(medicoId, e), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{medicoId}/especialidade/{especialidadeId}")
	public ResponseEntity<?> removerEspecialidade(@PathVariable Long medicoId, @PathVariable Long especialidadeId) {
		try {
			fachada.removerEspecialidade(medicoId, especialidadeId);
			return new ResponseEntity<String>("Especialidade removida com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}