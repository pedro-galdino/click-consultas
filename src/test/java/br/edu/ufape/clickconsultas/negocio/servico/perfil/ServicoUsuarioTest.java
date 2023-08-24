package br.edu.ufape.clickconsultas.negocio.servico.perfil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoUsuario;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Usuario;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;
import br.edu.ufape.clickconsultas.negocios.servico.perfil.InterfaceServicoUsuario;


@SpringBootTest
class ServicoUsuarioTest {
	
	@Autowired
	private InterfaceColecaoUsuario colecaoUsuario;
	
	@Autowired 
	private InterfaceServicoUsuario servicoUsuario;

	
	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException {
		Paciente paciente = new Paciente();
		Medico medico = new Medico();
		colecaoUsuario.save(medico);
		colecaoUsuario.save(paciente);
		
		assertEquals(medico, servicoUsuario.buscarPorId(medico.getId()));
		assertEquals(paciente, servicoUsuario.buscarPorId(paciente.getId()));
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdInexistente() {
		long id = 234L;
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoUsuario.buscarPorId(id));
	}
	
	@Test
	@Transactional
	void testarBuscarPorNomeExistente() {
		String nome = " Carlos Eduardo ";
		Paciente paciente = new Paciente();
		paciente.setNome(nome);
		colecaoUsuario.save(paciente);
		List<Usuario> lista1 = servicoUsuario.buscarPorNome(nome);
		List<Usuario> lista2 = servicoUsuario.buscarPorNome("Carlos eduardo");
		
		assertTrue(lista1.contains(paciente));
		assertTrue(lista2.contains(paciente));
		
		String nome2 = " Carlos Eduardo ";
		Medico medico = new Medico();
		medico.setNome(nome2);
		colecaoUsuario.save(medico);
		List<Usuario> lista3 = servicoUsuario.buscarPorNome(nome);
		List<Usuario> lista4 = servicoUsuario.buscarPorNome("Carlos eduardo");
		
		assertTrue(lista3.contains(paciente));
		assertTrue(lista4.contains(paciente));
	}

	@Test
	@Transactional
	void testarListaBuscarPorNomeExistente() {
		String nome = "João da Silva ";
		String teste = "nao encontra";
		Medico m1 = new Medico();
		Medico m2 = new Medico();
		Medico m3 = new Medico();
		m1.setNome("João da Silva");
		m2.setNome(teste);
		m3.setNome(" João da Silva  ");
		colecaoUsuario.save(m1);
		colecaoUsuario.save(m2);
		colecaoUsuario.save(m3);

		List<Usuario> lista = servicoUsuario.buscarPorNome(nome);

		assertEquals(lista.size(), 2);
	}


	@Test
	@Transactional
	void testarBuscarPorNomeInexistente() {
		String nome = " Carlos Eduardo ";
		Paciente paciente = new Paciente();
		paciente.setNome(nome);
		colecaoUsuario.save(paciente);
		List<Usuario> lista1 = servicoUsuario.buscarPorNome("Roberto César");
		List<Usuario> lista2 = servicoUsuario.buscarPorNome("Julio Roberio");
		try {
			if(!lista1.contains(paciente))
				if(!lista2.contains(paciente))
					return;
		}catch(Exception e) {
			fail();
		}
		
		}
	
	@Test
	@Transactional
	void testarBuscarPorEmail() throws ObjetoNaoEncontradoException {
		Medico medico = new Medico();
		String email = "exemplo@exemplo.com";
		medico.setEmail(email);
		colecaoUsuario.save(medico);
		
		assertEquals(medico, servicoUsuario.buscarPorEmail(email));
	}
	
	@Test
	@Transactional
	void testarBuscarPorEmailInexistente() {
		Paciente paciente = new Paciente();
		String email = "teste@teste.com";
		paciente.setEmail(email);
		colecaoUsuario.save(paciente);
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoUsuario.buscarPorEmail("exemplo@exemplo.com"));
	}
	
	@Test
	@Transactional
	void testarBuscarPorCpf() throws ObjetoNaoEncontradoException {
		Paciente paciente = new Paciente();
		String cpf = "111222333-45";
		paciente.setCpf(cpf);
		colecaoUsuario.save(paciente);
		
		assertEquals(paciente, servicoUsuario.buscarPorCpf(cpf));
		
	}
	
	@Test
	@Transactional
	void testarBuscarPorCpfInexistente() {
		Medico medico = new Medico();
		String cpf = "999888777-65";
		medico.setCpf(cpf);
		colecaoUsuario.save(medico);
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoUsuario.buscarPorCpf("111222333-45"));
	}
	
	
	@Test
	@Transactional
	void testarRemovePacienterPorId() throws ObjetoNaoEncontradoException {
		Paciente p = new Paciente();
		colecaoUsuario.save(p);
		
		int tamanhoInicial = colecaoUsuario.findAll().size();
		
		servicoUsuario.remover(p.getId());
		
		int tamanhoFinal = colecaoUsuario.findAll().size();
		
		assertEquals(tamanhoInicial-1, tamanhoFinal);
	}
	
	@Test
	@Transactional
	void testarRemoverMedicoPorId() throws ObjetoNaoEncontradoException {
		Medico medico = new Medico();
		colecaoUsuario.save(medico);
		
		int tamanhoInicial = colecaoUsuario.findAll().size();
		
		servicoUsuario.remover(medico.getId());
		
		int tamanhoFinal = colecaoUsuario.findAll().size();
		
		assertEquals(tamanhoFinal, tamanhoInicial-1);
		
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdInexistente() {
		long id = 32423L;
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoUsuario.remover(id));
	}
	
	@Test
	@Transactional
	void testarSalvarEmailExistente() {
		String email = "testeclickconsultas@gmail.com";
		Medico medico = new Medico();
		medico.setEmail(email);
		Paciente paciente = new Paciente();
		paciente.setEmail(email);

		assertThrows(ObjetoEmUsoException.class, () -> {
			servicoUsuario.salvar(medico);
			servicoUsuario.salvar(paciente);
		});
	}

	@Test
	@Transactional
	void testarSalvarCpfExistente() {
		String cpf = "188199177-60";
		Medico medico1 = new Medico();
		medico1.setCpf(cpf);
		Medico medico2 = new Medico();
		medico2.setCpf(cpf);

		assertThrows(ObjetoEmUsoException.class, () -> {
			servicoUsuario.salvar(medico1);
			servicoUsuario.salvar(medico2);
		});
	
	}
	

	@Test
	@Transactional
	void testarSalvarMedico() throws ObjetoEmUsoException {
		Medico medico = new Medico();
		try {
			servicoUsuario.salvar(medico);
			return;
		} catch (ObjetoEmUsoException e) {
			fail();
		}
	}
	
	@Test
	@Transactional
	void testarBuscarCarteiraPorUsuarioId() throws ObjetoNaoEncontradoException {
		Medico medico = new Medico();
		Carteira carteira = new Carteira(0, null);
		medico.setCarteira(carteira);
		colecaoUsuario.save(medico);
		
		assertEquals(carteira, servicoUsuario.buscarCarteiraPorUsuarioId(medico.getId()));
	
	}
	
	@Test
	@Transactional
	void testarBuscarCarteiraPorUsuarioIs() {
		long id = 2342L;
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoUsuario.buscarCarteiraPorUsuarioId(id));
	}

	@Test
	@Transactional
	void testarSalvarCarteira() throws ObjetoNaoEncontradoException {
		Paciente paciente = new Paciente();
		Carteira carteira = new Carteira(123.4, null);
		colecaoUsuario.save(paciente);
		servicoUsuario.salvarCarteira(paciente.getId(), carteira);
		
		assertEquals(carteira.getSaldo(), paciente.getCarteira().getSaldo());
		assertEquals(carteira.getChavesPix(), paciente.getCarteira().getChavesPix());
	}
	
	@Test
	@Transactional
	void testarSalvarCarteiraPorUsuarioInexistente() {
		Carteira carteira = new Carteira(123.4, null);
		long id = 1235L;
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoUsuario.salvarCarteira(id, carteira) );
	}
	
	@Test
	@Transactional
	void testarBuscarPixPorIds() throws ObjetoNaoEncontradoException {
		Paciente paciente = new Paciente();
		Carteira carteira = new Carteira();
		Pix pix = new Pix();
		List<Pix> pixs = List.of(pix);
		carteira.setChavesPix(pixs);
		paciente.setCarteira(carteira);
	
		colecaoUsuario.save(paciente);
		
		if(paciente.getId() != 0 && pix.getId() != 0) {
			assertEquals(pix, servicoUsuario.buscarPixPorId(paciente.getId(), pix.getId()));
			return;
		}else {
			fail();
		}
	}
	
	@Test
	@Transactional
	void testarBuscarPixPorIdsUsuarioInexistente() {
		long id = 123L;
		Paciente paciente = new Paciente();
		Carteira carteira = new Carteira();
		Pix pix = new Pix();
		List<Pix> pixs = List.of(pix);
		carteira.setChavesPix(pixs);
		paciente.setCarteira(carteira);
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoUsuario.buscarPixPorId(id, pix.getId()));
	}
	
	@Test
	@Transactional
	void testarBuscarPixPorIdsPixInexistente() {
		long id = 123L;
		Paciente paciente = new Paciente();
		Carteira carteira = new Carteira();
		Pix pix = new Pix();
		List<Pix> pixs = List.of(pix);
		carteira.setChavesPix(pixs);
		paciente.setCarteira(carteira);
		
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoUsuario.buscarPixPorId(paciente.getId(), id));
	}
		
	@Test
	@Transactional
	void testarSalvarPixCarteira() {
	    Pix pix = new Pix("698562695", "celular");
	    Paciente paciente = new Paciente();
	    Carteira carteira = new Carteira();
	    paciente.setCarteira(carteira);
	    colecaoUsuario.save(paciente);

	    try {
	    	servicoUsuario.salvarPixCarteira(paciente.getId(), pix);
			return;
		}catch (Exception e) {
			System.out.println(e);
			fail();
		}
	}

	@Test
	@Transactional
	void testarRemoverPixCarteiraInexistente() {
		Paciente paciente = new Paciente();
		Carteira carteira = new Carteira();
		Pix pix = new Pix();
		List<Pix> pixs = List.of(pix);
		carteira.setChavesPix(pixs);
		paciente.setCarteira(carteira);
		colecaoUsuario.save(paciente);
		try {
			servicoUsuario.removerPixCarteira(paciente.getId(), pix.getId());
			return;
		}catch (Exception e) {
			System.out.println(e);
			fail();
		}
		
	}
	
}
