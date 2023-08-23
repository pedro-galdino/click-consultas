package br.edu.ufape.clickconsultas.dados.perfil;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;

@Repository
public interface InterfaceColecaoMedico extends JpaRepository<Medico, Long> {
		
	public List<Medico> findByEspecialidadesNomeContainingIgnoreCase(String nomeEspecialidade);
	
	public Medico findByEspecialidadesNomeAndEspecialidadesNumeroRQE(String nome, int numeroRQE);
	
	public Medico findByCrmUfAndCrmNumero(String ufCrm, int numeroCrm);
	
}