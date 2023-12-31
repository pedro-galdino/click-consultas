package br.edu.ufape.clickconsultas.dados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.Consulta;

@Repository
public interface InterfaceColecaoConsulta extends JpaRepository<Consulta, Long> {

	List<Consulta> findAllByPacienteId(long pacienteId);
	
	List<Consulta> findAllByMedicoId(long medicoId);

	Consulta findByAgendamentoId(long agendamentoId);
	
}
