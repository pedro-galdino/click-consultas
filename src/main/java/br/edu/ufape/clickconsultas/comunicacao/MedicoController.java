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
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioNaoEncontradoException;

@RestController
@RequestMapping("/api/medico")
public class MedicoController {
	@Autowired
	private Fachada fachada;

	@GetMapping()
	public ResponseEntity<List<Medico>> buscarMedicos() {
		return new ResponseEntity<List<Medico>>(fachada.buscarMedicos(), HttpStatus.OK);
	}

	@GetMapping("/{medicoId}")
	public ResponseEntity<?> buscarMedicoPorId(@PathVariable Long medicoId) {
		try {
			return new ResponseEntity<Medico>(fachada.buscarMedicoPorId(medicoId), HttpStatus.OK);
		} catch (UsuarioNaoEncontradoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<?> cadastrarMedico(@RequestBody Medico medico) {
		try {
			return new ResponseEntity<Medico>(fachada.salvarMedico(medico), HttpStatus.CREATED);
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

	@PatchMapping("/senha/{medicoId}")
	public ResponseEntity<?> atualizarSenhaMedico(@RequestBody String senha, @PathVariable Long medicoId) {
		try {
			Medico medicoAtualizado = fachada.buscarMedicoPorId(medicoId);
			medicoAtualizado.setSenha(senha);
			return new ResponseEntity<Medico>(fachada.salvarMedico(medicoAtualizado), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{medicoId}")
	public ResponseEntity<?> removerMedico(@PathVariable Long medicoId) {
		try {
			fachada.removerMedico(medicoId);
			return new ResponseEntity<String>("Removido com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}