package br.edu.ufape.clickconsultas.negocios.servico.suporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.suporte.InterfaceColecaoAdministrador;
import br.edu.ufape.clickconsultas.negocios.modelo.suporte.Administrador;

@Service
public class ServicoAdministrador {

	@Autowired
	private InterfaceColecaoAdministrador colecaoAdministrador;
	
	public List<Administrador> buscarTodos(){
		return colecaoAdministrador.findAll();
	}
	
	public Administrador buscarPorId(long id) {
		return colecaoAdministrador.findById(id).orElse(null);
	}
	
	public List<Administrador> buscarPorNome(String nome){
		return colecaoAdministrador.findByNome(nome);
	}
	
	public Administrador buscarPorEmail(String email) {
		return colecaoAdministrador.findByEmail(email);
	}
	
	public Administrador salvar(Administrador administrador) {
		return colecaoAdministrador.save(administrador);
	}
	
	public void remover(long id) {
		colecaoAdministrador.deleteById(id);
	}
	
}
