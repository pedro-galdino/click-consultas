package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoEspecialidade;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Especialidade;

@Service
public class ServicoEspecialidade {

	@Autowired
	private InterfaceColecaoEspecialidade colecaoEspecialidade;
	
	public List<Especialidade> buscarTodos(){
		return colecaoEspecialidade.findAll();
	}
	
	public List<Especialidade> buscarPorNome(String nome){
		return colecaoEspecialidade.findByNome(nome);
	}
	
	public Especialidade buscarPorId(long id) {
		return colecaoEspecialidade.findById(id).orElse(null);
	}
	
	public Especialidade salvar(Especialidade especialidade) {
		return colecaoEspecialidade.save(especialidade);
	}
	
	public void remover(long id) {
		colecaoEspecialidade.deleteById(id);
	}
		
}
