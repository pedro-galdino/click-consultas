package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPaciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;

@Service
public class ServicoPaciente {
	@Autowired
	private InterfaceColecaoPaciente colecaoPaciente;
	
	public List<Paciente> buscarTodos(){
		return colecaoPaciente.findAll();
	}
	
	public List<Paciente> buscarPorNome(String nome){
		return colecaoPaciente.findByNome(nome);
	}
	
	public Paciente buscarPorEmail(String email) {
		return colecaoPaciente.findByEmail(email);
	}
	
	public Paciente buscarPorId(long id) {
		return colecaoPaciente.findById(id).orElse(null);
	}
	
	public Paciente salvar(Paciente paciente) {
		return colecaoPaciente.save(paciente);
	}
	
	public void remover(long id) {
		colecaoPaciente.deleteById(id);
	}
	
}