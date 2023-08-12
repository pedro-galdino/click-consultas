package br.edu.ufape.clickconsultas.dados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;

@Repository
public interface InterfaceColecaoAgenda extends JpaRepository<Agenda, Long> {
	
	public List<Agenda> findByEspecialidadeMedica(String especialidadeMedica);

}
