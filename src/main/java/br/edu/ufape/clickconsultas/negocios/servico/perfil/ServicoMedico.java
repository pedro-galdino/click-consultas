package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.EnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Especialidade;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EspecialidadesExcedidasException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ListaVaziaException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoMedico implements InterfaceServicoMedico {
	@Autowired
	private InterfaceColecaoMedico colecaoMedico;

	public Medico buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Medico m = colecaoMedico.findById(id).orElse(null);
		if (m == null)
			throw new ObjetoNaoEncontradoException("o", "medico");
		return m;
	}

	public Medico salvar(Medico medico) {
		return colecaoMedico.save(medico);
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

	// --- CRM ---

	public List<CRM> buscarCrms(long medicoId) throws ObjetoNaoEncontradoException, ListaVaziaException {
		Medico m = buscarPorId(medicoId);
		if (m.getCrm() == null || m.getCrm().isEmpty())
			throw new ListaVaziaException("CRM");

		return m.getCrm();
	}

	public CRM buscarCrmPorId(long medicoId, long crmId) throws ObjetoNaoEncontradoException, ListaVaziaException {
		Medico m = buscarPorId(medicoId);
		if (m.getCrm() == null || m.getCrm().isEmpty())
			throw new ListaVaziaException("CRM");

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
		m.adicionarCrm(crm);
		return salvar(m).getCrm();
	}

	public void removerCrm(long medicoId, long crmId)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException, ListaVaziaException {
		Medico m = buscarPorId(medicoId);
		CRM crm = buscarCrmPorId(medicoId, crmId);
		m.removerCrm(crm);
		salvar(m);
	}

	// --- Especialidade ---

	public List<Especialidade> buscarEspecialidades(long medicoId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		Medico m = buscarPorId(medicoId);
		if (m.getEspecialidades() == null || m.getEspecialidades().isEmpty())
			throw new ListaVaziaException("especialidade");

		return m.getEspecialidades();
	}

	public Especialidade buscarEspecialidadePorId(long medicoId, long especialidadeId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		Medico m = buscarPorId(medicoId);
		if (m.getEspecialidades() == null || m.getEspecialidades().isEmpty())
			throw new ListaVaziaException("especialidade");

		for (Especialidade e : m.getEspecialidades())
			if (e.getId() == especialidadeId)
				return e;

		throw new ObjetoNaoEncontradoException("a", "especialidade");
	}

	public List<Especialidade> salvarEspecialidade(long medicoId, Especialidade especialidade)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException, EspecialidadesExcedidasException {
		Medico m = buscarPorId(medicoId);

		if (m.getEspecialidades().size() == 2)
			throw new EspecialidadesExcedidasException();

		if (colecaoMedico.findByEspecialidadesNomeAndEspecialidadesNumeroRQE(especialidade.getNome(), especialidade.getNumeroRQE()) != null)
			throw new ObjetoEmUsoException("a", "especialidade");

		m.adicionarEspecialidade(especialidade);
		return salvar(m).getEspecialidades();
	}

	public void removerEspecialidade(long medicoId, Long especialidadeId)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException, ListaVaziaException {
		Medico m = buscarPorId(medicoId);
		Especialidade e = buscarEspecialidadePorId(medicoId, especialidadeId);
		m.removerEspecialidade(e);
		salvar(m);
	}

	// --- Endereço Médico ---

	public List<EnderecoMedico> buscarEnderecos(long medicoId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		Medico m = buscarPorId(medicoId);
		if (m.getEnderecos() == null || m.getEnderecos().isEmpty())
			throw new ListaVaziaException("endereço médico");

		return m.getEnderecos();
	}

	public EnderecoMedico buscarEnderecoPorId(long medicoId, long enderecoId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		Medico m = buscarPorId(medicoId);
		if (m.getEnderecos() == null || m.getEnderecos().isEmpty())
			throw new ListaVaziaException("endereço médico");

		for (EnderecoMedico endereco : m.getEnderecos())
			if (endereco.getId() == enderecoId)
				return endereco;

		throw new ObjetoNaoEncontradoException("o", "endereço médico");
	}

	public List<EnderecoMedico> salvarEndereco(long medicoId, EnderecoMedico endereco) throws ObjetoNaoEncontradoException {
		Medico m = buscarPorId(medicoId);
		m.adicionarEndereco(endereco);
		return salvar(m).getEnderecos();
	}

	public void removerEndereco(long medicoId, Long enderecoId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		Medico m = buscarPorId(medicoId);
		EnderecoMedico endereco = buscarEnderecoPorId(medicoId, enderecoId);
		m.removerEndereco(endereco);
		salvar(m);
	}

}