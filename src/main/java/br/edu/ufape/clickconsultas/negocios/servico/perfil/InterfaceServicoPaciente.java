package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.PlanoDeSaude;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

public interface InterfaceServicoPaciente {

	Paciente buscarPorId(long id) throws ObjetoNaoEncontradoException;

	Paciente salvar(Paciente paciente);

	PlanoDeSaude buscarPlanoDeSaude(long pacienteId) throws ObjetoNaoEncontradoException;

	PlanoDeSaude salvarPlanoDeSaude(long pacienteId, PlanoDeSaude plano) throws ObjetoNaoEncontradoException;

	void removerPlanoDeSaude(long pacienteId) throws ObjetoNaoEncontradoException;

}