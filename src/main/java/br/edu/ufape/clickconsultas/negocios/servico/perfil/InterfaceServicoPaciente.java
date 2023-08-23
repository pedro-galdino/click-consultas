package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.PlanoDeSaude;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoPaciente {

	List<Paciente> buscarTodos();

	List<Paciente> buscarPorNome(String nome);
	
	Paciente buscarPorCpf(String cpf) throws ObjetoNaoEncontradoException;

	Paciente buscarPorEmail(String email) throws ObjetoNaoEncontradoException;

	Paciente buscarPorId(long id) throws ObjetoNaoEncontradoException;

	Paciente salvar(Paciente paciente) throws ObjetoEmUsoException;

	void remover(long id) throws ObjetoNaoEncontradoException;
	
	public PlanoDeSaude buscarPlanoDeSaude(long pacienteId) throws ObjetoNaoEncontradoException;

	public PlanoDeSaude salvarPlanoDeSaude(long pacienteId, PlanoDeSaude plano) throws ObjetoNaoEncontradoException;
	
	public void removerPlanoDeSaude(long pacienteId) throws ObjetoNaoEncontradoException;
	
	public Carteira buscarCarteiraPorId(long pacienteId) throws ObjetoNaoEncontradoException;
	
	public Carteira salvarCarteira(long pacienteId, Carteira carteira) throws ObjetoNaoEncontradoException ;

}