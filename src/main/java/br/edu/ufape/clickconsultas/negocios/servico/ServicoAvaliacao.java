package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoAvaliacao;
import br.edu.ufape.clickconsultas.negocios.modelo.Avaliacao;



@Service
public class ServicoAvaliacao implements InterfaceServicoAvaliacao {
	@Autowired
	private InterfaceColecaoAvaliacao colecaoAvaliacao;
	
	public List<Avaliacao> buscarTodos() {
		return colecaoAvaliacao.findAll();
	}

	public Avaliacao buscarPorId(long id) {
		return colecaoAvaliacao.findById(id).orElse(null);
	}

	public Avaliacao salvar(Avaliacao avaliacao) {
		return colecaoAvaliacao.save(avaliacao);		
	}

	public void remover(long id) {
		colecaoAvaliacao.deleteById(id);
	}
}