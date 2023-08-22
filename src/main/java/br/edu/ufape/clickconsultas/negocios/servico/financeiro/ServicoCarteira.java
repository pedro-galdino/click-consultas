package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoCarteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoCarteira implements InterfaceServicoCarteira {
	@Autowired
	private InterfaceColecaoCarteira colecaoCarteira;

	public List<Carteira> buscarTodos() {
		return colecaoCarteira.findAll();
	}

	public Carteira buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Carteira c = colecaoCarteira.findById(id).orElse(null);
		if(c == null)
			throw new ObjetoNaoEncontradoException("a", "carteira");
		return c;
	}

	public Carteira salvar(Carteira carteira) {
		return colecaoCarteira.save(carteira);
	}

	public void remover(long carteiraId) throws ObjetoNaoEncontradoException {
		Carteira c = buscarPorId(carteiraId);
		if(c == null)
			throw new ObjetoNaoEncontradoException("a", "carteira");
		colecaoCarteira.deleteById(carteiraId);
	}

}