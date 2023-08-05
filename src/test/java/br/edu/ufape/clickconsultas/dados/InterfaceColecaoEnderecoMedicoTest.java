package br.edu.ufape.clickconsultas.dados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.EnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;

@SpringBootTest
class InterfaceColecaoEnderecoMedicoTest {
	@Autowired
	private InterfaceColecaoEnderecoMedico colecaoEnderecoMedico;
	@Autowired
	private InterfaceColecaoMedico colecaoMedico;
	
	@Test
	void cadastrarEnderecoMedicoTest() {
		long qtdEndereco = colecaoEnderecoMedico.count();
		Medico m = new Medico();
		EnderecoMedico e = new EnderecoMedico("Garanhuns", "PE", "12345-000", "Heliópolis", "Rua São Bento", 2, null, m);

		colecaoMedico.save(m);
		colecaoEnderecoMedico.save(e);
		long novaQtdEndereco = colecaoEnderecoMedico.count();

		assertEquals(qtdEndereco + 1, novaQtdEndereco);
	}
	
	@Test
	void removerEnderecoMedicoTest() {
		Medico m = new Medico();
		EnderecoMedico e = new EnderecoMedico("Garanhuns", "PE", "12345-000", "Heliópolis", "Rua São Bento", 2, null, m);
		colecaoMedico.save(m);
		colecaoEnderecoMedico.save(e);
		long qtdEndereco = colecaoEnderecoMedico.count();

		colecaoEnderecoMedico.deleteById(e.getId());
		long novaQtdEndereco = colecaoEnderecoMedico.count();

		assertEquals(qtdEndereco - 1, novaQtdEndereco);
	}
	
}
