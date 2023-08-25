package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.EnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Especialidade;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EspecialidadesExcedidasException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ListaVaziaException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoMedico {

	Medico buscarPorId(long id) throws ObjetoNaoEncontradoException;

	Medico salvar(Medico medico);

	Medico buscarPorCrm(String uf, int numero) throws ObjetoNaoEncontradoException;

	List<Medico> buscarPorNomeEspecialidade(String nomeEspecialidade) throws ObjetoNaoEncontradoException;

	Medico buscarPorEspecialidade(String nome, int numeroRQE) throws ObjetoNaoEncontradoException;

	List<CRM> buscarCrms(long medicoId) throws ObjetoNaoEncontradoException, ListaVaziaException;

	CRM buscarCrmPorId(long medicoId, long crmId) throws ObjetoNaoEncontradoException, ListaVaziaException;

	List<CRM> salvarCrm(long medicoId, CRM crm) throws ObjetoNaoEncontradoException, ObjetoEmUsoException;

	void removerCrm(long medicoId, long crmId) throws ObjetoNaoEncontradoException, ObjetoEmUsoException, ListaVaziaException;

	List<Especialidade> buscarEspecialidades(long medicoId) throws ObjetoNaoEncontradoException, ListaVaziaException;

	Especialidade buscarEspecialidadePorId(long medicoId, long especialidadeId) throws ObjetoNaoEncontradoException, ListaVaziaException;

	List<Especialidade> salvarEspecialidade(long medicoId, Especialidade e)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException, EspecialidadesExcedidasException, ListaVaziaException;

	void removerEspecialidade(long medicoId, Long especialidadeId)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException, ListaVaziaException;

	public List<EnderecoMedico> buscarEnderecos(long medicoId) throws ObjetoNaoEncontradoException, ListaVaziaException;

	public EnderecoMedico buscarEnderecoPorId(long medicoId, long enderecoId) throws ObjetoNaoEncontradoException, ListaVaziaException;

	public List<EnderecoMedico> salvarEndereco(long medicoId, EnderecoMedico endereco)
			throws ObjetoNaoEncontradoException;

	public void removerEndereco(long medicoId, Long enderecoId) throws ObjetoNaoEncontradoException, ListaVaziaException;

}