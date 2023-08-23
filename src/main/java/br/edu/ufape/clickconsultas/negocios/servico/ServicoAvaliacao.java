package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoAvaliacao;
import br.edu.ufape.clickconsultas.negocios.modelo.Avaliacao;
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
		if(avaliacao == null) 
			throw new ObjetoNaoEncontradoException("a", "avaliacao");
		
		return avaliacao;
			
	}

	public Avaliacao salvar(Avaliacao avaliacao) {
		return colecaoAvaliacao.save(avaliacao);	
		
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Avaliacao avaliacao = buscarPorId(id);
		if(avaliacao != null)
			colecaoAvaliacao.deleteById(id);
		
	}
}