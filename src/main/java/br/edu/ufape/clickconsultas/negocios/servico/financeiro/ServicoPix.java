package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoPix;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoPix implements InterfaceServicoPix {
	@Autowired
	private InterfaceColecaoPix colecaoPix;

	public List<Pix> buscarTodos() {
		return colecaoPix.findAll();
	}

	public Pix buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Pix p = colecaoPix.findById(id).orElse(null);
		if (p == null)
			throw new ObjetoNaoEncontradoException("o", "pix");
		return p;
	}

	public Pix salvar(Pix pix) {
		return colecaoPix.save(pix);
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Pix p = colecaoPix.findById(id).orElse(null);
		if (p == null)
			throw new ObjetoNaoEncontradoException("o", "pix");
		colecaoPix.deleteById(id);
	}

}