package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoAgendamento;
import br.edu.ufape.clickconsultas.negocios.modelo.Agendamento;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoAgendamento implements InterfaceServicoAgendamento {
	@Autowired
	private InterfaceColecaoAgendamento colecaoAgendamento;

	public List<Agendamento> buscarTodos() {
		return colecaoAgendamento.findAll();
	}

	public Agendamento buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Agendamento agendamento = colecaoAgendamento.findById(id).orElse(null);
		if (agendamento == null)
			throw new ObjetoNaoEncontradoException("o", "agendamento");
		return agendamento;
	}

	public List<Agendamento> buscarPorPacienteId(long pacienteId) {
		List<Agendamento> agendamentos = colecaoAgendamento.findAllByPacienteId(pacienteId);
		return agendamentos;
	}
	
	public List<Agendamento> buscarPorMedicoId(long medicoId) {
		List<Agendamento> agendamentos = colecaoAgendamento.findAllByAgendaMedicoId(medicoId);
		return agendamentos;
	}
	
	public Agendamento salvar(Agendamento agendamento) {
		return colecaoAgendamento.save(agendamento);
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Agendamento agendamento = buscarPorId(id);
		colecaoAgendamento.deleteById(agendamento.getId());
	}

}