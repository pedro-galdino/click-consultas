package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoAgendamento;
import br.edu.ufape.clickconsultas.negocios.modelo.Agendamento;



@Service
public class ServicoAgendamento {
	@Autowired
	private InterfaceColecaoAgendamento colecaoAgendamento;
	
	public List<Agendamento> buscarTodos() {
		return colecaoAgendamento.findAll();
	}
	
	public Agendamento buscarPorId(long id) {
		return colecaoAgendamento.findById(id).orElse(null);
	}

	public Agendamento salvar(Agendamento agendamento) {
		return colecaoAgendamento.save(agendamento);		
	}

	public void remover(long id) {
		colecaoAgendamento.deleteById(id);
	}
}