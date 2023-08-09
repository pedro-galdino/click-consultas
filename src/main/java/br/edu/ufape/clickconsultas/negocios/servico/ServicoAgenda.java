package br.edu.ufape.clickconsultas.negocios.servico;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoAgenda;
import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;


@Service
public class ServicoAgenda {
	@Autowired
	private InterfaceColecaoAgenda colecaoAgenda;
	
	public List<Agenda> buscarTodos() {
		return colecaoAgenda.findAll();
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
