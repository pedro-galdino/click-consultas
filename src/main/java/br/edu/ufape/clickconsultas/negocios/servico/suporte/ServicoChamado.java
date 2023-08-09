package br.edu.ufape.clickconsultas.negocios.servico.suporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.suporte.InterfaceColecaoChamado;
import br.edu.ufape.clickconsultas.negocios.modelo.suporte.Chamado;

@Service
public class ServicoChamado {

	@Autowired
	private InterfaceColecaoChamado colecaoChamado;
	
	public List<Chamado> buscarTodos(){
		return colecaoChamado.findAll();
	}
	
	//buscarPorProtocolo
	public Chamado buscarPorId(long id) {
		return colecaoChamado.findById(id).orElse(null);
	}
	
	public Chamado salvar(Chamado chamado) {
		return colecaoChamado.save(chamado);
	}
	
	public void remover(long id) {
		colecaoChamado.deleteById(id);
	}
	
}
