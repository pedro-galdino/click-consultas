package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Especialidade;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EspecialidadesExcedidasException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoMedico {

	List<Medico> buscarTodos();

	Medico buscarPorId(long id) throws ObjetoNaoEncontradoException;

	List<Medico> buscarPorNome(String nome);

	Medico buscarPorCpf(String cpf) throws ObjetoNaoEncontradoException;

	Medico buscarPorEmail(String email) throws ObjetoNaoEncontradoException;

	Medico buscarPorCrm(String uf, int numero) throws ObjetoNaoEncontradoException;

	List<Medico> buscarPorNomeEspecialidade(String nomeEspecialidade) throws ObjetoNaoEncontradoException;

	Medico buscarPorEspecialidade(String nome, int numeroRQE) throws ObjetoNaoEncontradoException;

	Medico salvar(Medico medico) throws ObjetoEmUsoException;

	void remover(long id) throws ObjetoNaoEncontradoException;

	List<CRM> buscarCrms(long medicoId) throws ObjetoNaoEncontradoException;

	CRM buscarCrmPorId(long medicoId, long crmId) throws ObjetoNaoEncontradoException;

	List<CRM> salvarCrm(long medicoId, CRM crm) throws ObjetoNaoEncontradoException, ObjetoEmUsoException;

	void removerCrm(long medicoId, long crmId) throws ObjetoNaoEncontradoException, ObjetoEmUsoException;

	List<Especialidade> buscarEspecialidades(long medicoId) throws ObjetoNaoEncontradoException;

	Especialidade buscarEspecialidadePorId(long medicoId, long especialidadeId) throws ObjetoNaoEncontradoException;

	List<Especialidade> salvarEspecialidade(long medicoId, Especialidade especialidade)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException, EspecialidadesExcedidasException;

	void removerEspecialidade(long medicoId, Long especialidadeId)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException;
	
	public Carteira buscarCarteiraPorMedicoId(long medicoId) throws ObjetoNaoEncontradoException;
	
	public Carteira salvarCarteira(long medicoId, Carteira carteira) throws ObjetoNaoEncontradoException;
	
	public Pix buscarPixPorId(long medicoId, long pixId) throws ObjetoNaoEncontradoException;

	public List<Pix> salvarPixCarteira(long medicoId, Pix pix) throws ObjetoNaoEncontradoException;

	public void removerPixCarteira(long medicoId, long pixId) throws ObjetoNaoEncontradoException;

}