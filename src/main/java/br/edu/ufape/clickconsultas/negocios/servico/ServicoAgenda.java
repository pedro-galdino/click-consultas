package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoAgenda;
import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;
import br.edu.ufape.clickconsultas.negocios.servico.exception.DadosInsuficientesException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoAgenda implements InterfaceServicoAgenda {
	@Autowired
	private InterfaceColecaoAgenda colecaoAgenda;

	public List<Agenda> buscarTodos() {
		return colecaoAgenda.findAll();
	}

	public Agenda buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Agenda agenda = colecaoAgenda.findById(id).orElse(null);
		if (agenda == null)
			throw new ObjetoNaoEncontradoException("a", "agenda");
		return agenda;
	}

	public Agenda salvar(Agenda agenda) throws DadosInsuficientesException {
		if (agenda.verificarAtributos() == true)
			throw new DadosInsuficientesException();
		return colecaoAgenda.save(agenda);
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Agenda agenda = colecaoAgenda.findById(id).orElse(null);
		if (agenda == null)
			throw new ObjetoNaoEncontradoException("a", "agenda");
		colecaoAgenda.deleteById(id);
	}

}