package br.edu.ufape.clickconsultas.dados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.Avaliacao;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;

@Repository
public interface InterfaceColecaoAvaliacao extends JpaRepository<Avaliacao, Long> {

	public List<Avaliacao> findByNota(double nota);

	public List<Avaliacao> findByPaciente(Paciente paciente);

}
