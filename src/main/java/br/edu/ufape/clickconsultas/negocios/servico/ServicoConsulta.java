package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoConsulta;
import br.edu.ufape.clickconsultas.negocios.modelo.Consulta;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoConsulta implements InterfaceServicoConsulta {
	@Autowired
	private InterfaceColecaoConsulta colecaoConsulta;
	
	public List<Consulta> buscarTodos() {
		return colecaoConsulta.findAll();
		
	}
	
	public Consulta buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Consulta consulta =  colecaoConsulta.findById(id).orElse(null);
		if(consulta == null) 
			throw new ObjetoNaoEncontradoException("a", "consulta");
		
		return consulta;
		
	}

	public Consulta salvar(Consulta consulta) {
		return colecaoConsulta.save(consulta);
		
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Consulta consulta = buscarPorId(id);
		if(consulta != null)
			colecaoConsulta.deleteById(id);
		
	}
}