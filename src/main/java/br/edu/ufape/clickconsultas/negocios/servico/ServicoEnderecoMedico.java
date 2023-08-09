package br.edu.ufape.clickconsultas.negocios.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoEnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.EnderecoMedico;


@Service
public class ServicoEnderecoMedico {
	@Autowired
	private InterfaceColecaoEnderecoMedico colecaoEnderecoMedico;
	
	public List<EnderecoMedico> buscarTodos() {
		return colecaoEnderecoMedico.findAll();
	}
	
	public EnderecoMedico buscarPorId(long id) {
		return colecaoEnderecoMedico.findById(id).orElse(null);
	}

	public EnderecoMedico salvar(EnderecoMedico enderecoMedico) {
		return colecaoEnderecoMedico.save(enderecoMedico);		
	}

	public void remover(long id) {
		colecaoEnderecoMedico.deleteById(id);
	}
}