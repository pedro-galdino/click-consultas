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

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Especialidade;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;
import br.edu.ufape.clickconsultas.negocios.servico.perfil.InterfaceServicoMedico;

@SpringBootTest
class ServicoMedicoTest {
	
	@Autowired
	private InterfaceServicoMedico servicoMedico;
	@Autowired
	private InterfaceColecaoMedico colecaoMedico;
	

	@Test
	@Transactional
	void testarSalvarMedicoEmailExistente() {
		String email = "testeclickconsultas@gmail.com";
		Medico medico1 = new Medico();
		medico1.setEmail(email);
		Medico medico2 = new Medico();
		medico2.setEmail(email);
		
		ObjetoEmUsoException exception = assertThrows(ObjetoEmUsoException.class, () -> {
			servicoMedico.salvar(medico1);
			servicoMedico.salvar(medico2);
		});
	}
	
	@Test
	@Transactional
	void testarSalvarMedicoCpfExistente() {
		String cpf = "188199177-60";
		Medico medico1 = new Medico();
		medico1.setCpf(cpf);
		Medico medico2 = new Medico();
		medico2.setCpf(cpf);
		
		ObjetoEmUsoException exception = assertThrows(ObjetoEmUsoException.class, () -> {
			servicoMedico.salvar(medico1);
			servicoMedico.salvar(medico2);
		});
	}
	
	@Test
	@Transactional
	void testarSalvarMedicoCrmExistente() {
		Medico medico1 = new Medico();
		Medico medico2 = new Medico();
		CRM crm1 = new CRM("PE", 185158);
		CRM crm2 = new CRM("BA", 4684651);
		List<CRM> crms = List.of(crm1, crm2);
		medico1.setCrm(crms);
		medico2.setCrm(crms);
		
		ObjetoEmUsoException exception = assertThrows(ObjetoEmUsoException.class, () -> {
			servicoMedico.salvar(medico1);
			servicoMedico.salvar(medico2);
		});
		
	}
	
	@Test
	@Transactional
	void testarSalvarMedicoCrmExistenteEmUmaListaNaoUnitaria() {
		Medico medico1 = new Medico();
		Medico medico2 = new Medico();
		CRM crm1 = new CRM("PE", 185158);
		CRM crm2 = new CRM("BA", 4684651);
		CRM crm3 = new CRM("PA", 6844885);
		List<CRM> crms1 = List.of(crm1, crm2);
		List<CRM> crms2 = List.of(crm1,crm3);
		medico1.setCrm(crms1);
		medico2.setCrm(crms2);
		
		ObjetoEmUsoException exception = assertThrows(ObjetoEmUsoException.class, () -> {
			servicoMedico.salvar(medico1);
			servicoMedico.salvar(medico2);
		});
	}
	
	@Test
	@Transactional
	void testarSalvarMedicoCrmInexistente() throws ObjetoEmUsoException {
		Medico medico1 = new Medico();
		CRM crm1 = new CRM("PE", 185158);
		CRM crm2 = new CRM("BA", 4684651);
		List<CRM> crms1 = List.of(crm1, crm2);
		medico1.setCrm(crms1);

		servicoMedico.salvar(medico1);
		
		assertEquals(medico1, colecaoMedico.findByCrmNumero(185158));
	}
	
	@Test 
	@Transactional
	void testarSalvarMedico() throws ObjetoEmUsoException{
		Medico medico = new Medico();
		try {
			servicoMedico.salvar(medico);
			return;
		} catch (ObjetoEmUsoException e) {
			fail();
		}
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdInexistente() throws ObjetoNaoEncontradoException{
		long inexistente = 234L;
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoMedico.buscarPorId(inexistente));
	}
	
	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException{
		Medico medico = new Medico();
		colecaoMedico.save(medico);
		assertEquals(medico, servicoMedico.buscarPorId(medico.getId()));
	}
	
	@Test
	@Transactional
	void testarBuscarPorCpfInexistente() {
		String cpf = "11796314760";
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoMedico.buscarPorCpf(cpf));
	}
	
	@Test
	@Transactional
	void testarBuscarPorCpfExistente() throws ObjetoNaoEncontradoException {
		String cpf = "11796314760";
		Medico medico = new Medico();
		medico.setCpf(cpf);
		colecaoMedico.save(medico);
		assertEquals(medico, servicoMedico.buscarPorCpf(cpf));
	}
	
	@Test
	@Transactional
	void testarBuscarPorEmailInexistente() {
		String email = "clickconsulTas@gmail.com ";
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoMedico.buscarPorEmail(email));
	}
	
	@Test
	@Transactional
	void testarBuscarPorEmailExistente() throws ObjetoNaoEncontradoException {
		String email = "clickconsultas@gmail.com";
		Medico medico = new Medico();
		medico.setEmail("clickconsulTas@gmail.com");
		colecaoMedico.save(medico);
		assertEquals(medico, servicoMedico.buscarPorEmail(email));
	}
	
	@Test
	@Transactional
	void testarBuscarPorEmailExistenteComMaiusculasMinusculas() throws ObjetoNaoEncontradoException {
		String email = "cliCkconsultas@Gmail.com ";
		Medico medico = new Medico();
		medico.setEmail("clickconsulTas@gmail.com  ");
		colecaoMedico.save(medico);
		assertEquals(medico, servicoMedico.buscarPorEmail(email));
	}
	
	@Test
	@Transactional
	void testarBuscarPorEmailExistenteComEspacoEmBranco() throws ObjetoNaoEncontradoException {
		String email = "  clickconsultas@gmail.com  ";
		Medico medico = new Medico();
		medico.setEmail("clickconsulTas@gmail.com  ");
		colecaoMedico.save(medico);
		assertEquals(medico, servicoMedico.buscarPorEmail(email));
	}
	
	@Test
	@Transactional
	void testarBuscarPorNomeInexistente() {
		String nome = "clickConsultas";
		List<Medico> lista = servicoMedico.buscarPorNome(nome);
		assertTrue(lista.isEmpty());
	}
	
	@Test
	@Transactional
	void testarBuscarPorNomeExistente() {
		String nome = "click     ConsuLtas  ";
		String teste = "naoEncontra";
		Medico m1 = new Medico(); 
		Medico m2 = new Medico();
		Medico m3 = new Medico();
		m1.setNome("click Consultas ");
		m2.setNome(teste);
		m3.setNome("cLick Consultas   ");
		colecaoMedico.save(m1);
		colecaoMedico.save(m2);
		colecaoMedico.save(m3);
		

		List<Medico> lista = servicoMedico.buscarPorNome(nome);
		
		System.out.println(m1.getNome());
		assertEquals(lista.size(), 2);
	}
	
	
	@Test
	@Transactional
	void testarBuscarPorCrmInexistente() {
		int crm = 1234;
		assertThrows(ObjetoNaoEncontradoException.class, ()-> servicoMedico.buscarPorCrm(crm));
	}
	
	@Test
	@Transactional
	void testarBuscarPorCrmExistente() throws ObjetoNaoEncontradoException {
		CRM crm = new CRM("PE", 152936);
		List<CRM> crms = List.of(crm);
		Medico medico = new Medico();
		medico.setCrm(crms);
		colecaoMedico.save(medico);
		
		assertEquals(medico, servicoMedico.buscarPorCrm(152936));
		
	}
	
	@Test
	@Transactional
	void testarBuscarPorCrmEmListas() throws ObjetoNaoEncontradoException {
		CRM crm1 = new CRM("PE", 152936);
		CRM crm2 = new CRM("PE", 519156);
		List<CRM> crms = List.of(crm1, crm2);
		Medico medico = new Medico();
		medico.setCrm(crms);
		colecaoMedico.save(medico);
		
		assertEquals(medico, servicoMedico.buscarPorCrm(152936));
	}
	
	@Test
	@Transactional
	void testarBuscarPorCrmEmListasComMultiplosMedicos() throws ObjetoNaoEncontradoException {
		CRM crm1 = new CRM("PE", 152936);
		CRM crm2 = new CRM("PE", 519156);
		List<CRM> crms1 = List.of(crm1, crm2);
		CRM crm3 = new CRM("BA", 511658);
		CRM crm4 = new CRM("PA", 981556);
		List<CRM> crms2 = List.of(crm3, crm4);
		Medico medico1 = new Medico();
		Medico medico2 = new Medico();
		medico1.setCrm(crms1);
		medico2.setCrm(crms2);
		colecaoMedico.save(medico1);
		colecaoMedico.save(medico2);
		
		assertEquals(medico2, servicoMedico.buscarPorCrm(981556));
	}
	
	@Test
	@Transactional
	void testarBuscarPorEspecialidadeInexistente() throws ObjetoNaoEncontradoException {
		String especialidade = "cardiologia";
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoMedico.buscarPorEspecialidade(especialidade));
	}
	
	@Test
	@Transactional
	void testarBuscarPorEspecialidadeInexistenteComOutrasExistente() {
		Medico medico = new Medico();
		Especialidade especialidade = new Especialidade("cardiologia", 158596);
		List<Especialidade> especialidades = List.of(especialidade);
		medico.setEspecialidades(especialidades);
		colecaoMedico.save(medico);
		
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoMedico.buscarPorEspecialidade("neurologia"));
		
	}
	
	@Test
	@Transactional
	void testarBuscarPorEspecialidadeExistente() throws ObjetoNaoEncontradoException {
		String especialidadeNome = "cardiologia";
		Medico medico = new Medico();
		Especialidade especialidade = new Especialidade("cardiologia", 158596);
		List<Especialidade> especialidades = List.of(especialidade);
		medico.setEspecialidades(especialidades);
		colecaoMedico.save(medico);
		
		List<Medico> lista = servicoMedico.buscarPorEspecialidade(especialidadeNome);
		
		assertTrue(lista.contains(medico));
	}
	
	@Test
	@Transactional
	void testarBuscarPorEspecialidadeExistenteComVariacaoDeMaiusculasMinusculas() throws ObjetoNaoEncontradoException {
		String especialidadeNome = "CardioloGia";
		Medico medico = new Medico();
		Especialidade especialidade = new Especialidade("cardiologia", 158596);
		List<Especialidade> especialidades = List.of(especialidade);
		medico.setEspecialidades(especialidades);
		colecaoMedico.save(medico);
		
		List<Medico> lista = servicoMedico.buscarPorEspecialidade(especialidadeNome);
		
		assertTrue(lista.contains(medico));
	}
	
	@Test
	@Transactional
	void testarBuscarPorEspecialidadeExistenteComEspacoEmBranco() throws ObjetoNaoEncontradoException {
		String especialidadeNome = "cArDiologia   ";
		Medico medico = new Medico();
		Especialidade especialidade = new Especialidade("cardiologia", 158596);
		List<Especialidade> especialidades = List.of(especialidade);
		medico.setEspecialidades(especialidades);
		colecaoMedico.save(medico);
		
		List<Medico> lista = servicoMedico.buscarPorEspecialidade(especialidadeNome);
		
		assertTrue(lista.contains(medico));
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdInexistente() {
		long inexistente = 234235L;
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoMedico.remover(inexistente));
	}
	
	@Test
	@Transactional
	void testarRemoverPorIdExistente() throws ObjetoNaoEncontradoException {
		Medico medico = new Medico();
		colecaoMedico.save(medico);
		List<Medico> lista = colecaoMedico.findAll();
		int tamanhoInicial = lista.size();
		
		servicoMedico.remover(medico.getId());
		
		lista = colecaoMedico.findAll();
		int tamanhoFinal = lista.size();
		
		assertEquals(tamanhoInicial-1, tamanhoFinal);
	}
		
}
