package br.edu.ufape.clickconsultas.negocios.servico;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoHorarioAgendado;
import br.edu.ufape.clickconsultas.negocios.modelo.HorarioAgendado;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoHorarioAgendado implements InterfaceServicoHorarioAgendado {
	@Autowired
	private InterfaceColecaoHorarioAgendado colecaoHorarioAgendado;
	
	public List<HorarioAgendado> buscarTodos() {
		return colecaoHorarioAgendado.findAll();
		
	}
	
	public List<HorarioAgendado> buscarPorData(LocalDate data) throws ObjetoNaoEncontradoException {
		List<HorarioAgendado> lista = colecaoHorarioAgendado.findByData(data);
		
		if(lista.isEmpty())
			throw new ObjetoNaoEncontradoException("o", "horario");
		
		return lista;
		
	}
	
	public HorarioAgendado buscarPorId(long id) throws ObjetoNaoEncontradoException {
		HorarioAgendado h = colecaoHorarioAgendado.findById(id).orElse(null);
		
		if(h == null)
			throw new ObjetoNaoEncontradoException("o", "horario");
		
		return h;
		
	}

	public HorarioAgendado salvar(HorarioAgendado horarioAgendado) {
		return colecaoHorarioAgendado.save(horarioAgendado);		
	}
	
	public void remover(long id) throws ObjetoNaoEncontradoException {
		HorarioAgendado h = buscarPorId(id);
		
		if(h != null) 
			colecaoHorarioAgendado.deleteById(id);
		
	}

}