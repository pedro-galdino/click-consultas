package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioInexistenteException;

@Service
public class ServicoMedico implements InterfaceServicoMedico {
	@Autowired
	private InterfaceColecaoMedico colecaoMedico;

	public List<Medico> buscarTodos() {
		return colecaoMedico.findAll();
	}

	public Medico buscarPorId(long id) {
		return colecaoMedico.findById(id).orElse(null);
	}

	public List<Medico> buscarPorNome(String nome) {
		return colecaoMedico.findByNome(nome);
	}

	public Medico buscarPorEmail(String email) throws UsuarioInexistenteException {
		Medico m = colecaoMedico.findByEmail(email);
		if (m == null) 
			throw new UsuarioInexistenteException(email);
		
		return m;
	}

	public Medico salvar(Medico medico) {
		return colecaoMedico.save(medico);
	}

	public void remover(long id) {
		colecaoMedico.deleteById(id);
	}

}