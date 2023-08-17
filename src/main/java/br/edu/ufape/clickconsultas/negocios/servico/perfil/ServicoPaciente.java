package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPaciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CpfExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EmailExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioInexistenteException;

@Service
public class ServicoPaciente implements InterfaceServicoPaciente {
	@Autowired
	private InterfaceColecaoPaciente colecaoPaciente;

	public List<Paciente> buscarTodos() {
		return colecaoPaciente.findAll();
	}

	public List<Paciente> buscarPorNome(String nome) {
		return colecaoPaciente.findByNome(nome);
	}

	public Paciente buscarPorEmail(String email) throws UsuarioInexistenteException {
		Paciente p = colecaoPaciente.findByEmail(email);
		if (p == null) 
			throw new UsuarioInexistenteException();
		
		return p;
	}

	public Paciente buscarPorId(long id) throws UsuarioInexistenteException{
		Paciente p = colecaoPaciente.findById(id).orElse(null);
		if (p == null) 
			throw new UsuarioInexistenteException();
		
		return p;
	}
	
	public Paciente buscarPorCpf(String cpf) {
		return colecaoPaciente.findByCpf(cpf);
	}

	public Paciente salvar(Paciente paciente) throws EmailExistenteException, CpfExistenteException {
		Paciente pacienteExistenteByEmail = colecaoPaciente.findByEmail(paciente.getEmail());
		if(pacienteExistenteByEmail != null) 
			throw new EmailExistenteException(paciente.getEmail());
		
		Paciente pacienteExistenteByCpf = colecaoPaciente.findByCpf(paciente.getCpf());
	    if (pacienteExistenteByCpf != null) 
	        throw new CpfExistenteException(paciente.getCpf());
	    
		return colecaoPaciente.save(paciente);
	}

	public void remover(long id) {
		colecaoPaciente.deleteById(id);
	}

}