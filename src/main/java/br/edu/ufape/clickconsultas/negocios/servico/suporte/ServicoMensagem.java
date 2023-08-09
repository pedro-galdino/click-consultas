package br.edu.ufape.clickconsultas.negocios.servico.suporte;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.suporte.InterfaceColecaoMensagem;
import br.edu.ufape.clickconsultas.negocios.modelo.suporte.Mensagem;

@Service
public class ServicoMensagem {

	@Autowired
	private InterfaceColecaoMensagem colecaoMensagem;
	
	public List<Mensagem> buscarTodos(){
		return colecaoMensagem.findAll();
	}
	
	public Mensagem buscarPorId(long id) {
		return colecaoMensagem.findById(id).orElse(null);
	}
	
	public List<Mensagem> findByIdAutor(long id){
		return colecaoMensagem.findByIdAutor(id);
	}
	
	public List<Mensagem> findByAutor(String autor){
		return colecaoMensagem.findByAutor(autor);
	}

	public List<Mensagem> findByDatahora(LocalDateTime datahora){
		return colecaoMensagem.findByDatahora(datahora);
	}
	
	public Mensagem salvar(Mensagem mensagem) {
		return colecaoMensagem.save(mensagem);
	}
	
	public void remover(long id) {
		colecaoMensagem.deleteById(id);
	}
}
