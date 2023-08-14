package br.edu.ufape.clickconsultas.negocios.servico;

import java.time.LocalDate;
import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.HorarioAgendado;

public interface InterfaceServicoHorarioAgendado {

	List<HorarioAgendado> buscarTodos();

	List<HorarioAgendado> BuscarPorData(LocalDate data);

	HorarioAgendado buscarPorId(long id);

	HorarioAgendado salvar(HorarioAgendado horarioAgendado);

	void remover(long id);

}