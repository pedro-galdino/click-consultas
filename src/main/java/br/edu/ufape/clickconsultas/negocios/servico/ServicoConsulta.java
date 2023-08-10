package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoConsulta;
import br.edu.ufape.clickconsultas.negocios.modelo.Consulta;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;

@Service
public class ServicoConsulta {
	@Autowired
	private InterfaceColecaoConsulta colecaoConsulta;
	
	public List<Consulta> buscarTodos() {
		return colecaoConsulta.findAll();
	}
	
	public List<Consulta> buscarPorMedico(Medico medico) {
		return colecaoConsulta.findByMedico(medico);
	}
		
	public List<Consulta> buscarPorPaciente(Paciente paciente) {
		return colecaoConsulta.findByPaciente(paciente);
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