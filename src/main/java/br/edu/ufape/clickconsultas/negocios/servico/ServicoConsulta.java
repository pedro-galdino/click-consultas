package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoConsulta;
import br.edu.ufape.clickconsultas.negocios.modelo.Consulta;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ListaVaziaException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoConsulta implements InterfaceServicoConsulta {
	@Autowired
	private InterfaceColecaoConsulta colecaoConsulta;

	public List<Consulta> buscarTodos() {
		return colecaoConsulta.findAll();
	}

	public Consulta buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Consulta consulta = colecaoConsulta.findById(id).orElse(null);
		if (consulta == null)
			throw new ObjetoNaoEncontradoException("a", "consulta");

		return consulta;
	}

	public List<Consulta> buscarPorPacienteId(long pacienteId) throws ObjetoNaoEncontradoException, ListaVaziaException {
		List<Consulta> consultas = colecaoConsulta.findAllByPacienteId(pacienteId);
		if (consultas.isEmpty() || consultas == null)
			throw new ListaVaziaException("consulta");

		return consultas;
	}

	public List<Consulta> buscarPorMedicoId(long medicoId) throws ObjetoNaoEncontradoException, ListaVaziaException {
		List<Consulta> consultas = colecaoConsulta.findAllByMedicoId(medicoId);
		if (consultas.isEmpty() || consultas == null)
			throw new ListaVaziaException("consulta");

		return consultas;
	}
	
	public Consulta buscarPorAgendamentoId(long agendamentoId) {
		Consulta consulta = colecaoConsulta.findByAgendamentoId(agendamentoId);
		return consulta;
	}

	public Consulta salvar(Consulta consulta) {
		return colecaoConsulta.save(consulta);
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Consulta consulta = buscarPorId(id);
		colecaoConsulta.deleteById(consulta.getId());
	}

}