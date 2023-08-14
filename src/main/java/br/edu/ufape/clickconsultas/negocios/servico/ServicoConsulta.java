package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoConsulta;
import br.edu.ufape.clickconsultas.negocios.modelo.Consulta;

@Service
public class ServicoConsulta implements InterfaceServicoConsulta {
	@Autowired
	private InterfaceColecaoConsulta colecaoConsulta;
	
	public List<Consulta> buscarTodos() {
		return colecaoConsulta.findAll();
	}
	
	public Consulta buscarPorId(long id) {
		return colecaoConsulta.findById(id).orElse(null);
	}

	public Consulta salvar(Consulta consulta) {
		return colecaoConsulta.save(consulta);		
	}

	public void remover(long id) {
		colecaoConsulta.deleteById(id);
	}
}