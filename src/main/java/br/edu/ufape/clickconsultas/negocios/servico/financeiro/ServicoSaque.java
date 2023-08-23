package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoSaque;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Saque;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoSaque implements InterfaceServicoSaque {
	@Autowired
	private InterfaceColecaoSaque colecaoSaque;

	public List<Saque> buscarTodos() {
		return colecaoSaque.findAll();
	}

	public List<Saque> buscarPorData(Date data) throws ObjetoNaoEncontradoException{
		List<Saque> lista = colecaoSaque.findByData(data);
		if(lista.isEmpty())
			throw new ObjetoNaoEncontradoException("o", "saque");
		
		return lista;
	}

	public Saque buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Saque saque = colecaoSaque.findById(id).orElse(null);
		
		if(saque == null)
			throw new ObjetoNaoEncontradoException("o", "saque");
		
		return saque;
	}
	
	public List<Saque> buscarPorCarteiraId(long carteiraId) throws ObjetoNaoEncontradoException {
		List<Saque> saques = colecaoSaque.findByCarteiraId(carteiraId);
		if(saques.isEmpty())
			throw new ObjetoNaoEncontradoException("o", "saque");
		return saques;
	}

	public Saque salvar(Saque saque) {
		return colecaoSaque.save(saque);
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Saque saque = buscarPorId(id);
		if(saque != null)
			colecaoSaque.deleteById(id);
	}

}