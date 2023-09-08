package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoAvaliacao;
import br.edu.ufape.clickconsultas.negocios.modelo.Avaliacao;
import br.edu.ufape.clickconsultas.negocios.servico.exception.NotaDeAvaliacaoInvalidaException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoAvaliacao implements InterfaceServicoAvaliacao {
	@Autowired
	private InterfaceColecaoAvaliacao colecaoAvaliacao;

	public List<Avaliacao> buscarTodos() {
		return colecaoAvaliacao.findAll();
	}

	public Avaliacao buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Avaliacao avaliacao = colecaoAvaliacao.findById(id).orElse(null);
		if (avaliacao == null)
			throw new ObjetoNaoEncontradoException("a", "avaliacao");
		return avaliacao;
	}

	public List<Avaliacao> buscarPorPacienteId(long pacienteId) {
		List<Avaliacao> avaliacoes = colecaoAvaliacao.findByPacienteId(pacienteId);
		return avaliacoes;
	}

	public Avaliacao salvar(Avaliacao avaliacao) throws NotaDeAvaliacaoInvalidaException {
		int min = 0, max = 5;
		if (avaliacao.getNota() < min || avaliacao.getNota() > max)
			throw new NotaDeAvaliacaoInvalidaException(min, max);
		return colecaoAvaliacao.save(avaliacao);
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Avaliacao avaliacao = buscarPorId(id);
		colecaoAvaliacao.deleteById(avaliacao.getId());
	}
	
	public List<Avaliacao> buscarAvaliacoesPorRegistroId(long id) throws ObjetoNaoEncontradoException {
		List<Avaliacao> avaliacoes = colecaoAvaliacao.findByRegistroId(id);
		return avaliacoes;
	}

}