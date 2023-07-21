package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.InterfaceColecaoUsuario;
import br.edu.ufape.clickconsultas.negocios.modelo.Usuario;

@SpringBootTest
class InterfaceColecaoUsuarioTest {
	@Autowired
	private InterfaceColecaoUsuario colecaoUsuario;
	
	@Test
	void cadastrarTest() {
		long qtdUsuario = colecaoUsuario.count();
		Date data = new Date();
		Usuario u = new Usuario(qtdUsuario, "João", "999.999.999-99", data, "Homem", "99999-9999", "email@email.com", "1234");
		
		colecaoUsuario.save(u);
		long novaQtdUsuario = colecaoUsuario.count();
		
		assertEquals(qtdUsuario + 1, novaQtdUsuario);
	}

}