package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoSaque;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Saque;

@Service
public class ServicoSaque implements InterfaceServicoSaque {
	@Autowired
	private InterfaceColecaoSaque colecaoSaque;

	public List<Saque> buscarTodos() {
		return colecaoSaque.findAll();
	}

	public List<Saque> buscarPorData(Date data) {
		return colecaoSaque.findByData(data);
	}

	public Saque buscarPorId(long id) {
		return colecaoSaque.findById(id).orElse(null);
	}

	public Saque salvar(Saque saque) {
		return colecaoSaque.save(saque);
	}

	public void remover(long id) {
		colecaoSaque.deleteById(id);
	}

}