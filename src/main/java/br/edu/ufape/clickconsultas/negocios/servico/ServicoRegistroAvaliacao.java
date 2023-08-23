package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoRegistroAvaliacao;
import br.edu.ufape.clickconsultas.negocios.modelo.RegistroAvaliacao;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;


@Service
public class ServicoRegistroAvaliacao implements InterfaceServicoRegistroAvaliacao {
	@Autowired
	private InterfaceColecaoRegistroAvaliacao colecaoRegistroAvaliacao;
	
	public List<RegistroAvaliacao> buscarTodos() {
		return colecaoRegistroAvaliacao.findAll();
		
	}
	
	public RegistroAvaliacao buscarPorId(long id) throws ObjetoNaoEncontradoException {
		RegistroAvaliacao registro = colecaoRegistroAvaliacao.findById(id).orElse(null);
		if(registro == null)
			throw new ObjetoNaoEncontradoException("o", "registro");
		
		return registro;
		
	}

	public RegistroAvaliacao salvar(RegistroAvaliacao registroAvaliacao) {
		return colecaoRegistroAvaliacao.save(registroAvaliacao);	
		
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		RegistroAvaliacao registro = buscarPorId(id);
		if(registro != null)
			colecaoRegistroAvaliacao.deleteById(id);
		
	}
}