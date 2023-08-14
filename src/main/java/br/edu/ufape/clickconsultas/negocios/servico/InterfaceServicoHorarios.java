package br.edu.ufape.clickconsultas.negocios.servico;

import java.time.LocalDate;
import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.Horarios;

public interface InterfaceServicoHorarios {

	List<Horarios> buscarTodos();

	List<Horarios> buscarPorData(LocalDate data);

	Horarios buscarPorId(long id);

	Horarios salvar(Horarios horario);

	void remover(long id);

}