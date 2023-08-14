package br.edu.ufape.clickconsultas.negocios.servico;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoHorarioAgendado;
import br.edu.ufape.clickconsultas.negocios.modelo.HorarioAgendado;

@Service
public class ServicoHorarioAgendado implements InterfaceServicoHorarioAgendado {
	@Autowired
	private InterfaceColecaoHorarioAgendado colecaoHorarioAgendado;
	
	public List<HorarioAgendado> buscarTodos() {
		return colecaoHorarioAgendado.findAll();
	}
	
	public List<HorarioAgendado> BuscarPorData(LocalDate data) {
		return colecaoHorarioAgendado.findByData(data);
	}
	
	public HorarioAgendado buscarPorId(long id) {
		return colecaoHorarioAgendado.findById(id).orElse(null);
	}

	public HorarioAgendado salvar(HorarioAgendado horarioAgendado) {
		return colecaoHorarioAgendado.save(horarioAgendado);		
	}
	
	public void remover(long id) {
		colecaoHorarioAgendado.deleteById(id);
	}
}