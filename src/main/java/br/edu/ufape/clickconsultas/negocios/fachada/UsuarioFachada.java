package br.edu.ufape.clickconsultas.negocios.fachada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.Medico;

@Component
public class UsuarioFachada {
	@Autowired
	private InterfaceColecaoMedico colecaoMedico;
	
	public Medico cadastrarMedico(String nome, String email, String senha) {
        Medico novoMedico = new Medico();
        novoMedico.setNome(nome);
        novoMedico.setEmail(email);
        novoMedico.setSenha(senha);
        return colecaoMedico.save(novoMedico);
    }
}
