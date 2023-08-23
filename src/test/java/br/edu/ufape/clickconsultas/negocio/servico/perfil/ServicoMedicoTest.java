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
	void testarSalvarMedicoCrmInexistente() throws ObjetoEmUsoException {
		Medico medico1 = new Medico();
		CRM crm1 = new CRM("PE", 185158);
		CRM crm2 = new CRM("BA", 4684651);
		List<CRM> crms1 = List.of(crm1, crm2);
		medico1.setCrm(crms1);

		servicoMedico.salvar(medico1);

		assertEquals(medico1, colecaoMedico.findByCrmUfAndCrmNumero(crm1.getUf(), crm1.getNumero()));
	}

	
	@Test
	@Transactional
	void testarSalvarMedico(){
		Medico medico = new Medico();
		try {
			servicoMedico.salvar(medico);
			return;
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Transactional
	void testarBuscarPorIdInexistente() throws ObjetoNaoEncontradoException {
		long inexistente = 234L;
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoMedico.buscarPorId(inexistente));
	}

	@Test
	@Transactional
	void testarBuscarPorIdExistente() throws ObjetoNaoEncontradoException {
		Medico medico = new Medico();
		colecaoMedico.save(medico);
		assertEquals(medico, servicoMedico.buscarPorId(medico.getId()));
	}

	@Test
	@Transactional
	void testarBuscarPorCrmInexistente() {
		CRM crm = new CRM ("RN", 1234);
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoMedico.buscarPorCrm(crm.getUf(), crm.getNumero()));
	}

	@Test
	@Transactional
	void testarBuscarPorCrmExistente() throws ObjetoNaoEncontradoException {
		CRM crm = new CRM("PE", 152936);
		List<CRM> crms = List.of(crm);
		Medico medico = new Medico();
		medico.setCrm(crms);
		colecaoMedico.save(medico);

		assertEquals(medico, servicoMedico.buscarPorCrm(crm.getUf(), crm.getNumero()));
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

		assertEquals(medico, servicoMedico.buscarPorCrm(crm1.getUf(), crm1.getNumero()));
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

		assertEquals(medico2, servicoMedico.buscarPorCrm(crm4.getUf(), crm4.getNumero()));
	}

	@Test
	@Transactional
	void testarBuscarPorEspecialidadeInexistente() throws ObjetoNaoEncontradoException {
		String especialidade = "cardiologia";
		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoMedico.buscarPorNomeEspecialidade(especialidade));
	}

	@Test
	@Transactional
	void testarBuscarPorEspecialidadeInexistenteComOutrasExistente() {
		Medico medico = new Medico();
		Especialidade especialidade = new Especialidade("cardiologia", 158596);
		List<Especialidade> especialidades = List.of(especialidade);
		medico.setEspecialidades(especialidades);
		colecaoMedico.save(medico);

		assertThrows(ObjetoNaoEncontradoException.class, () -> servicoMedico.buscarPorNomeEspecialidade("neurologia"));

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

		List<Medico> lista = servicoMedico.buscarPorNomeEspecialidade(especialidadeNome);

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

		List<Medico> lista = servicoMedico.buscarPorNomeEspecialidade(especialidadeNome);

		assertTrue(lista.contains(medico));
	}

	@Test
	@Transactional
	void testarBuscarPorEspecialidadeExistenteComEspacoEmBranco() throws ObjetoNaoEncontradoException {
		String especialidadeNome = "cardiologia   ";
		Medico medico = new Medico();
		Especialidade especialidade = new Especialidade("cardiologia", 158596);
		List<Especialidade> especialidades = List.of(especialidade);
		medico.setEspecialidades(especialidades);
		colecaoMedico.save(medico);

		List<Medico> lista = servicoMedico.buscarPorNomeEspecialidade(especialidadeNome);

		assertTrue(lista.contains(medico));
	}

}
