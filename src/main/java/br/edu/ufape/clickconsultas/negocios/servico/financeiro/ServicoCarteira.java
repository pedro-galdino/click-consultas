package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoCarteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;


@Service
public class ServicoCarteira {
	@Autowired
	private InterfaceColecaoCarteira colecaoCarteira;
	
	public List<Carteira> buscarTodos() {
		return colecaoCarteira.findAll();
	}
	
	public Carteira buscarPorId(long id) {
		return colecaoCarteira.findById(id).orElse(null);
	}

	public Carteira salvar(Carteira carteira) {
		return colecaoCarteira.save(carteira);		
	}

	public void remover(long id) {
		colecaoCarteira.deleteById(id);
	}
}