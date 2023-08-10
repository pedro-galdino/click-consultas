package br.edu.ufape.clickconsultas.dados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;
import br.edu.ufape.clickconsultas.negocios.modelo.Agendamento;
import br.edu.ufape.clickconsultas.negocios.modelo.HorarioAgendado;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;

@Repository
public interface InterfaceColecaoAgendamento extends JpaRepository<Agendamento, Long> {

	public List<Agendamento> findByAgenda(Agenda agenda);

	public List<Agendamento> findByPaciente(Paciente paciente);

	public List<Agendamento> findByHorarioAgendado(HorarioAgendado horarioAgendado);

	public List<Agendamento> findByTipoConsulta(String tipoConsulta);

}
