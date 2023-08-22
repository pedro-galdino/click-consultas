package br.edu.ufape.clickconsultas.negocios.servico;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoHorarios;
import br.edu.ufape.clickconsultas.negocios.modelo.Horarios;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;


@Service
public class ServicoHorarios implements InterfaceServicoHorarios {
	@Autowired
	private InterfaceColecaoHorarios colecaoHorarios;
	
	public List<Horarios> buscarTodos() {
		return colecaoHorarios.findAll();
	}
	
	public List<Horarios> buscarPorData(LocalDate data) throws ObjetoNaoEncontradoException {
		List<Horarios> listaH = colecaoHorarios.findByData(data);
		if(listaH.isEmpty())
			throw new ObjetoNaoEncontradoException("o", "horario");
	
		return listaH;
	}
	
	public Horarios buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Horarios horario = colecaoHorarios.findById(id).orElse(null);
		if(horario == null)
			throw new ObjetoNaoEncontradoException("o", "horario");
		
		return horario;
	}

	public Horarios salvar(Horarios horario) {
		return colecaoHorarios.save(horario);		
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Horarios horario = buscarPorId(id);
		if(horario != null)
			colecaoHorarios.deleteById(id);
	}
}
