package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoRegistroAvaliacao;
import br.edu.ufape.clickconsultas.negocios.modelo.RegistroAvaliacao;


@Service
public class ServicoRegistroAvaliacao {
	@Autowired
	private InterfaceColecaoRegistroAvaliacao colecaoRegistroAvaliacao;
	
	public List<RegistroAvaliacao> buscarTodos() {
		return colecaoRegistroAvaliacao.findAll();
	}
	
	public List<RegistroAvaliacao> buscarPorNumeroAvaliacoes(int numeroAvaliacoes) {
		return colecaoRegistroAvaliacao.findByNumeroAvaliacoes(numeroAvaliacoes);
	}
	
	public RegistroAvaliacao buscarPorId(long id) {
		return colecaoRegistroAvaliacao.findById(id).orElse(null);
	}

	public RegistroAvaliacao salvar(RegistroAvaliacao registroAvaliacao) {
		return colecaoRegistroAvaliacao.save(registroAvaliacao);		
	}

	public void remover(long id) {
		colecaoRegistroAvaliacao.deleteById(id);
	}
}