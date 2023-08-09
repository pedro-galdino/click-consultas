package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoHorarios;
import br.edu.ufape.clickconsultas.negocios.modelo.Horarios;


@Service
public class ServicoHorarios {
	@Autowired
	private InterfaceColecaoHorarios colecaoHorarios;
	
	public List<Horarios> buscarTodos() {
		return colecaoHorarios.findAll();
	}
	
	public Horarios buscarPorId(long id) {
		return colecaoHorarios.findById(id).orElse(null);
	}

	public Horarios salvar(Horarios horario) {
		return colecaoHorarios.save(horario);		
	}

	public void remover(long id) {
		colecaoHorarios.deleteById(id);
	}
}
