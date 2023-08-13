package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoDeposito;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Deposito;

@Service
public class ServicoDeposito implements InterfaceServicoDeposito {
	@Autowired
	private InterfaceColecaoDeposito colecaoDeposito;

	public List<Deposito> buscarTodos() {
		return colecaoDeposito.findAll();
	}

	public List<Deposito> buscarPorData(Date data) {
		return colecaoDeposito.findByData(data);
	}

	public Deposito buscarPorId(long id) {
		return colecaoDeposito.findById(id).orElse(null);
	}

	public Deposito salvar(Deposito deposito) {
		return colecaoDeposito.save(deposito);
	}

	public void remover(long id) {
		colecaoDeposito.deleteById(id);
	}

}