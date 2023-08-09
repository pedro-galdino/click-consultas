package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoCRM;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;

@Service
public class ServicoCRM {
	
	@Autowired
	private InterfaceColecaoCRM colecaoCRM;
	
	public List<CRM> buscarTodos(){
		return colecaoCRM.findAll();
	}
	
	public CRM buscarPorId(long id) {
		return colecaoCRM.findById(id).orElse(null);
	}
	
	public CRM buscarPorNumeroUF(int numero, String Uf) {
		return colecaoCRM.findByNumeroAndUf(numero, Uf);
	}
	
	public CRM salvar(CRM crm) {
		return colecaoCRM.save(crm);
	}
	
	public void remover(long id) {
		colecaoCRM.deleteById(id);
	}
	
}
