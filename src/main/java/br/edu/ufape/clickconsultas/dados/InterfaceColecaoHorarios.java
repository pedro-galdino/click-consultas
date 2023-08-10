package br.edu.ufape.clickconsultas.dados;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.Horarios;

@Repository
public interface InterfaceColecaoHorarios extends JpaRepository<Horarios, Long> {

	public List<Horarios> findByData(LocalDate data);

}
