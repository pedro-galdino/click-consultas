package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoAgenda;
import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoAgenda implements InterfaceServicoAgenda {
	@Autowired
	private InterfaceColecaoAgenda colecaoAgenda;

	public List<Agenda> buscarTodos() {
		return colecaoAgenda.findAll();
	}
	
	public List<Agenda> buscarPorIdMedico(long medicoId) throws ObjetoNaoEncontradoException {
        List<Agenda> agendas = colecaoAgenda.findAllByMedicoId(medicoId);
        return agendas;
    }

	public Agenda buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Agenda agenda = colecaoAgenda.findById(id).orElse(null);
		if (agenda == null)
			throw new ObjetoNaoEncontradoException("a", "agenda");
		return agenda;
	}

	public Agenda salvar(Agenda agenda) {
		return colecaoAgenda.save(agenda);
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Agenda agenda = buscarPorId(id);
		colecaoAgenda.deleteById(agenda.getId());
	}
	
}