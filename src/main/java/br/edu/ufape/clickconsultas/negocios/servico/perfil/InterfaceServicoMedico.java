package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Especialidade;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EspecialidadesExcedidasException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoMedico {

	Medico buscarPorId(long id) throws ObjetoNaoEncontradoException;

	Medico salvar(Medico medico);

	Medico buscarPorCrm(String uf, int numero) throws ObjetoNaoEncontradoException;

	List<Medico> buscarPorNomeEspecialidade(String nomeEspecialidade) throws ObjetoNaoEncontradoException;

	Medico buscarPorEspecialidade(String nome, int numeroRQE) throws ObjetoNaoEncontradoException;

	List<CRM> buscarCrms(long medicoId) throws ObjetoNaoEncontradoException;

	CRM buscarCrmPorId(long medicoId, long crmId) throws ObjetoNaoEncontradoException;

	List<CRM> salvarCrm(long medicoId, CRM crm) throws ObjetoNaoEncontradoException, ObjetoEmUsoException;

	void removerCrm(long medicoId, long crmId) throws ObjetoNaoEncontradoException, ObjetoEmUsoException;

	List<Especialidade> buscarEspecialidades(long medicoId) throws ObjetoNaoEncontradoException;

	Especialidade buscarEspecialidadePorId(long medicoId, long especialidadeId) throws ObjetoNaoEncontradoException;

	List<Especialidade> salvarEspecialidade(long medicoId, Especialidade e)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException, EspecialidadesExcedidasException;

	void removerEspecialidade(long medicoId, Long especialidadeId)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException;

}