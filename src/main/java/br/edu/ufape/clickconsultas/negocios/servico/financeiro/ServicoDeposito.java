package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoDeposito;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Deposito;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoDeposito implements InterfaceServicoDeposito {
	@Autowired
	private InterfaceColecaoDeposito colecaoDeposito;

	public List<Deposito> buscarTodos() {
		return colecaoDeposito.findAll();
	}

	public List<Deposito> buscarPorData(Date data) throws ObjetoNaoEncontradoException{
		List<Deposito> lista = colecaoDeposito.findByData(data);
		if(lista.isEmpty())
			throw new ObjetoNaoEncontradoException("o", "deposito");
		
		return lista;
	}

	public Deposito buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Deposito deposito = colecaoDeposito.findById(id).orElse(null);
		
		if(deposito == null)
			throw new ObjetoNaoEncontradoException("o", "deposito");
		
		return deposito;
	}

	public Deposito salvar(Deposito deposito){
			return colecaoDeposito.save(deposito);

	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Deposito deposito = buscarPorId(id);
		if(deposito != null)
			colecaoDeposito.deleteById(id);
	}

}