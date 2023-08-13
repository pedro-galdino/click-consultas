package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.PlanoDeSaude;

public interface InterfaceServicoPlanoDeSaude {

	List<PlanoDeSaude> buscarTodos();

	PlanoDeSaude buscarPorNumero(int numero);

	PlanoDeSaude buscarPorId(long id);

	PlanoDeSaude salvar(PlanoDeSaude planoDeSaude);

	void remover(long id);

}