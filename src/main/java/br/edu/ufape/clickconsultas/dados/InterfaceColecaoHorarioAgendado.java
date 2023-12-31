package br.edu.ufape.clickconsultas.dados;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.HorarioAgendado;

@Repository
public interface InterfaceColecaoHorarioAgendado extends JpaRepository<HorarioAgendado, Long>{

	public List<HorarioAgendado> findByData(LocalDate data);

}
