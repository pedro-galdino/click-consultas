package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPaciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoPaciente implements InterfaceServicoPaciente {
	@Autowired
	private InterfaceColecaoPaciente colecaoPaciente;

	public List<Paciente> buscarTodos() {
		return colecaoPaciente.findAll();
	}

	public Paciente buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Paciente p = colecaoPaciente.findById(id).orElse(null);
		if (p == null)
			throw new ObjetoNaoEncontradoException("o", "paciente");
		return p;
	}

	public List<Paciente> buscarPorNome(String nome) {
		return colecaoPaciente.findByNomeContainingIgnoreCase(nome);
	}

	public Paciente buscarPorEmail(String email) throws ObjetoNaoEncontradoException {
		Paciente p = colecaoPaciente.findByEmailContainingIgnoreCase(email);
		if (p == null) 
			throw new ObjetoNaoEncontradoException("o", "paciente");
		return p;
	}

	public Paciente buscarPorCpf(String cpf) throws ObjetoNaoEncontradoException {
		Paciente p = colecaoPaciente.findByCpf(cpf);
		if (p == null) 
			throw new ObjetoNaoEncontradoException("o", "paciente");
		return p;
	}

	public Paciente salvar(Paciente paciente) throws ObjetoEmUsoException {
		Paciente pacienteExistenteByEmail = colecaoPaciente.findByEmailContainingIgnoreCase(paciente.getEmail());
		if (pacienteExistenteByEmail != null && paciente.getId() != pacienteExistenteByEmail.getId())
			throw new ObjetoEmUsoException("o", "e-mail");

		Paciente pacienteExistenteByCpf = colecaoPaciente.findByCpf(paciente.getCpf());
		if (pacienteExistenteByCpf != null && paciente.getId() != pacienteExistenteByCpf.getId())
			throw new ObjetoEmUsoException("o", "CPF");

		return colecaoPaciente.save(paciente);
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Paciente p = buscarPorId(id);
		if (p == null)
			throw new ObjetoNaoEncontradoException("o", "paciente");
		colecaoPaciente.deleteById(p.getId());
	}

}