package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;

@Service
public class ServicoMedico {
	
	@Autowired
	private InterfaceColecaoMedico colecaoMedico;
	
	public List<Medico> buscarTodos(){
		return colecaoMedico.findAll();
	}
	
	public Medico buscarPorId(long id) {
		return colecaoMedico.findById(id).orElse(null);
	}
	
	public List<Medico> buscarPorNome(String nome) {
		return colecaoMedico.findByNome(nome);
	}
	
	public Medico buscarPorEmail(String email) {
		return colecaoMedico.findByEmail(email);
	}
	
	public Medico salvar(Medico medico) {
		return colecaoMedico.save(medico);
	}
	
	public void remover(long id) {
		colecaoMedico.deleteById(id);
	}
	
}
