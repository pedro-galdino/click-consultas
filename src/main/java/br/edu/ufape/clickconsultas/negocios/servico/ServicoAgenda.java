package br.edu.ufape.clickconsultas.negocios.servico;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoAgenda;
import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;


@Service
public class ServicoAgenda {
	@Autowired
	private InterfaceColecaoAgenda colecaoAgenda;
	
	public List<Agenda> buscarTodos() {
		return colecaoAgenda.findAll();
	}
	
	public List<Agenda> buscarPorMedico(Medico medico) {
		return colecaoAgenda.findByMedico(medico);
	}
	
	public List<Agenda> buscarPorEspecialidade(String especialidadeMedica){
		return colecaoAgenda.findByEspecialidadeMedica(especialidadeMedica);
	}
	
	public Agenda buscarPorId(long id) {
		return colecaoAgenda.findById(id).orElse(null);
	}

	public Agenda salvar(Agenda agenda) {
		return colecaoAgenda.save(agenda);		
	}

	public void remover(long id) {
		colecaoAgenda.deleteById(id);
	}
}
