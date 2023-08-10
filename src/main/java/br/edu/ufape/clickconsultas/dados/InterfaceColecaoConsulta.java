package br.edu.ufape.clickconsultas.dados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.Consulta;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;

@Repository
public interface InterfaceColecaoConsulta extends JpaRepository<Consulta, Long> {

	public List<Consulta> findByMedico(Medico medico);

	public List<Consulta> findByPaciente(Paciente paciente);

}
