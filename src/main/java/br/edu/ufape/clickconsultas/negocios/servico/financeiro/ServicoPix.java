package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.financeiro.InterfaceColecaoPix;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;


@Service
public class ServicoPix {
	@Autowired
	private InterfaceColecaoPix colecaoPix;
	
	public List<Pix> buscarTodos() {
		return colecaoPix.findAll();
	}
	
	public Pix buscarPorId(long id) {
		return colecaoPix.findById(id).orElse(null);
	}

	public Pix salvar(Pix pix) {
		return colecaoPix.save(pix);		
	}

	public void remover(long id) {
		colecaoPix.deleteById(id);
	}
}