package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoEnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.EnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;


@Service
public class ServicoEnderecoMedico implements InterfaceServicoEnderecoMedico {
	@Autowired
	private InterfaceColecaoEnderecoMedico colecaoEnderecoMedico;
	
	public List<EnderecoMedico> buscarTodos() {
		return colecaoEnderecoMedico.findAll();
		
	}
		
	public EnderecoMedico buscarPorId(long id) throws ObjetoNaoEncontradoException {
		EnderecoMedico endereco = colecaoEnderecoMedico.findById(id).orElse(null);
		
		if(endereco == null) 
			throw new ObjetoNaoEncontradoException("o", "endereco");
		
		return endereco;
			
	}

	public EnderecoMedico salvar(EnderecoMedico enderecoMedico) {
		return colecaoEnderecoMedico.save(enderecoMedico);	
		
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		EnderecoMedico endereco = buscarPorId(id);
		if(endereco != null)
			colecaoEnderecoMedico.deleteById(id);
		
	}
}