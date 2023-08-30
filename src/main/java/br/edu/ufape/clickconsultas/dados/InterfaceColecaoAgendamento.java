package br.edu.ufape.clickconsultas.dados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.Agendamento;

@Repository
public interface InterfaceColecaoAgendamento extends JpaRepository<Agendamento, Long> {

	List<Agendamento> findAllByPacienteId(long pacienteId);
	
}
