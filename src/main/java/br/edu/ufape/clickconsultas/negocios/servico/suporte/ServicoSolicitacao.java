package br.edu.ufape.clickconsultas.negocios.servico.suporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.suporte.InterfaceColecaoSolicitacao;
import br.edu.ufape.clickconsultas.negocios.modelo.suporte.Solicitacao;

@Service
public class ServicoSolicitacao {
	
	@Autowired
	private InterfaceColecaoSolicitacao colecaoSolicitacao;
	
	public List<Solicitacao> buscarTodos(){
		return colecaoSolicitacao.findAll();
	}
	
	//buscarPorProtocolo
	public Solicitacao buscarPorId(long id) {
		return colecaoSolicitacao.findById(id).orElse(null);
	}
	
	public Solicitacao salvar(Solicitacao solicitacao) {
		return colecaoSolicitacao.save(solicitacao);
	}
	
	public void remover(long id) {
		colecaoSolicitacao.deleteById(id);
	}
}
