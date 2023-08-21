package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Especialidade;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EspecialidadesExcedidasException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoMedico implements InterfaceServicoMedico {
	@Autowired
	private InterfaceColecaoMedico colecaoMedico;

	public List<Medico> buscarTodos() {
		return colecaoMedico.findAll();
	}

	public Medico buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Medico m = colecaoMedico.findById(id).orElse(null);
		if (m == null)
			throw new ObjetoNaoEncontradoException("o", "medico");
		return m;
	}

	public List<Medico> buscarPorNome(String nome) {
		return colecaoMedico.findByNomeContainingIgnoreCase(nome.trim());
	}

	public Medico buscarPorCpf(String cpf) throws ObjetoNaoEncontradoException {
		Medico m = colecaoMedico.findByCpf(cpf);
		if (m == null)
			throw new ObjetoNaoEncontradoException("o", "medico");
		return m;
	}

	public Medico buscarPorEmail(String email) throws ObjetoNaoEncontradoException {
		Medico m = colecaoMedico.findByEmailContainingIgnoreCase(email.trim());
		if (m == null)
			throw new ObjetoNaoEncontradoException("o", "medico");
		return m;
	}

	public Medico buscarPorCrm(String uf, int numero) throws ObjetoNaoEncontradoException {
		Medico m = colecaoMedico.findByCrmUfAndCrmNumero(uf, numero);
		if (m == null)
			throw new ObjetoNaoEncontradoException("o", "medico");
		return m;
	}

	public List<Medico> buscarPorNomeEspecialidade(String nomeEspecialidade) throws ObjetoNaoEncontradoException {
		List<Medico> m = colecaoMedico.findByEspecialidadesNomeContainingIgnoreCase(nomeEspecialidade.trim());
		if (m.isEmpty())
			throw new ObjetoNaoEncontradoException("a", "especialidade");
		return m;
	}

	public Medico buscarPorEspecialidade(String nome, int numeroRQE) throws ObjetoNaoEncontradoException {
		Medico m = colecaoMedico.findByEspecialidadesNomeAndEspecialidadesNumeroRQE(nome, numeroRQE);
		if (m == null)
			throw new ObjetoNaoEncontradoException("a", "especialidade");
		return m;
	}

	public Medico salvar(Medico medico) throws ObjetoEmUsoException {
		// Verifica se o email já está em uso
		Medico medicoExistenteByEmail = colecaoMedico.findByEmailContainingIgnoreCase(medico.getEmail());
		if (medicoExistenteByEmail != null && medico.getId() != medicoExistenteByEmail.getId())
			throw new ObjetoEmUsoException("o", "e-mail");

		// Verifica se o cpf já está em uso
		Medico medicoExistenteByCpf = colecaoMedico.findByCpf(medico.getCpf());
		if (medicoExistenteByCpf != null && medico.getId() != medicoExistenteByCpf.getId())
			throw new ObjetoEmUsoException("o", "CPF");

		return colecaoMedico.save(medico);
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Medico m = buscarPorId(id);
		colecaoMedico.deleteById(m.getId());
	}
	
	// --- CRM ---

	public List<CRM> buscarCrms(long medicoId) throws ObjetoNaoEncontradoException {
		Medico m = buscarPorId(medicoId);
		return m.getCrm();
	}

	public CRM buscarCrmPorId(long medicoId, long crmId) throws ObjetoNaoEncontradoException {
		Medico m = buscarPorId(medicoId);
		for (CRM crm : m.getCrm())
			if (crm.getId() == crmId)
				return crm;
		throw new ObjetoNaoEncontradoException("o", "CRM");
	}

	public List<CRM> salvarCrm(long medicoId, CRM crm) throws ObjetoNaoEncontradoException, ObjetoEmUsoException {
		Medico medicoExistenteByCRM = colecaoMedico.findByCrmUfAndCrmNumero(crm.getUf(), crm.getNumero());
		if (medicoExistenteByCRM != null)
			throw new ObjetoEmUsoException("o", "CRM");

		Medico m = buscarPorId(medicoId);
		List<CRM> crms = m.getCrm();
		crms.add(crm);
		m.setCrm(crms);
		return salvar(m).getCrm();
	}

	public void removerCrm(long medicoId, long crmId) throws ObjetoNaoEncontradoException, ObjetoEmUsoException {
		Medico m = buscarPorId(medicoId);
		List<CRM> crms = m.getCrm();
		CRM crm = buscarCrmPorId(medicoId, crmId);
		crms.remove(crm);
		m.setCrm(crms);
		salvar(m);
	}

	// --- Especialidade ---
	
	public List<Especialidade> buscarEspecialidades(long medicoId) throws ObjetoNaoEncontradoException {
		Medico m = buscarPorId(medicoId);
		return m.getEspecialidades();
	}

	public Especialidade buscarEspecialidadePorId(long medicoId, long especialidadeId)
			throws ObjetoNaoEncontradoException {
		Medico m = buscarPorId(medicoId);
		for (Especialidade e : m.getEspecialidades())
			if (e.getId() == especialidadeId)
				return e;
		throw new ObjetoNaoEncontradoException("a", "especialidade");
	}

	public List<Especialidade> salvarEspecialidade(long medicoId, Especialidade e)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException, EspecialidadesExcedidasException {
		Medico m = buscarPorId(medicoId);
		List<Especialidade> especialidades = m.getEspecialidades();
		if (m.getEspecialidades().size() == 2)
			throw new EspecialidadesExcedidasException();

		if (colecaoMedico.findByEspecialidadesNomeAndEspecialidadesNumeroRQE(e.getNome(), e.getNumeroRQE()) != null)
			throw new ObjetoEmUsoException("a", "especialidade");

		especialidades.add(e);
		m.setEspecialidades(especialidades);
		return salvar(m).getEspecialidades();
	}

	public void removerEspecialidade(long medicoId, Long especialidadeId) throws ObjetoNaoEncontradoException, ObjetoEmUsoException {
		Medico m = buscarPorId(medicoId);
		List<Especialidade> especialidades = m.getEspecialidades();
		Especialidade e = buscarEspecialidadePorId(medicoId, especialidadeId);
		especialidades.remove(e);
		m.setEspecialidades(especialidades);
		salvar(m);
	}

}