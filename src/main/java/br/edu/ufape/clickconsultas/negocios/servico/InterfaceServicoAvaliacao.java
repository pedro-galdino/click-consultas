package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.Avaliacao;
import br.edu.ufape.clickconsultas.negocios.servico.exception.NotaDeAvaliacaoInvalidaException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoAvaliacao {

	List<Avaliacao> buscarTodos();

	Avaliacao buscarPorId(long id) throws ObjetoNaoEncontradoException;

	Avaliacao salvar(Avaliacao avaliacao) throws NotaDeAvaliacaoInvalidaException;

	void remover(long id) throws ObjetoNaoEncontradoException;

	List<Avaliacao> buscarPorPacienteId(long pacienteId);

	public Avaliacao buscarPorConsultaId(long id) throws ObjetoNaoEncontradoException;
	
	public List<Avaliacao> buscarAvaliacoesPorRegistroId(long id) throws ObjetoNaoEncontradoException;

}