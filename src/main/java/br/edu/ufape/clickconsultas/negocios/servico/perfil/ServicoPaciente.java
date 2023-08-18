package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPaciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CpfExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EmailExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioNaoEncontradoException;

@Service
public class ServicoPaciente implements InterfaceServicoPaciente {
	@Autowired
	private InterfaceColecaoPaciente colecaoPaciente;

	public List<Paciente> buscarTodos() {
		return colecaoPaciente.findAll();
	}

	public Paciente buscarPorId(long id) throws UsuarioNaoEncontradoException {
		Paciente p = colecaoPaciente.findById(id).orElse(null);
		if (p == null)
			throw new UsuarioNaoEncontradoException(id, "paciente");
		return p;
	}

	public List<Paciente> buscarPorNome(String nome) {
		return colecaoPaciente.findByNome(nome);
	}

	public Paciente buscarPorEmail(String email) throws UsuarioInexistenteException {
		Paciente p = colecaoPaciente.findByEmail(email);
		if (p == null)
			throw new UsuarioInexistenteException(email);
		return p;
	}

	public Paciente buscarPorCpf(String cpf) throws UsuarioInexistenteException {
		Paciente p = colecaoPaciente.findByCpf(cpf);
		if (p == null)
			throw new UsuarioInexistenteException(cpf);
		return p;
	}

	public Paciente salvar(Paciente paciente) throws EmailExistenteException, CpfExistenteException {
		Paciente pacienteExistenteByEmail = colecaoPaciente.findByEmail(paciente.getEmail());
		if (pacienteExistenteByEmail != null && paciente.getEmail() != pacienteExistenteByEmail.getEmail())
			throw new EmailExistenteException(paciente.getEmail());

		Paciente pacienteExistenteByCpf = colecaoPaciente.findByCpf(paciente.getCpf());
		if (pacienteExistenteByCpf != null && paciente.getCpf() != pacienteExistenteByCpf.getCpf())
			throw new CpfExistenteException(paciente.getCpf());

		return colecaoPaciente.save(paciente);
	}

	public void remover(long id) throws UsuarioNaoEncontradoException {
		Paciente p = buscarPorId(id);
		if (p == null)
			throw new UsuarioNaoEncontradoException(id, "paciente");
		colecaoPaciente.deleteById(p.getId());
	}

}