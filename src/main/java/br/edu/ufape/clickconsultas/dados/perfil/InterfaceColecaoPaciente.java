package br.edu.ufape.clickconsultas.dados.perfil;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;

@Repository
public interface InterfaceColecaoPaciente extends JpaRepository<Paciente, Long> {
	
	public List<Paciente> findByNomeContainingIgnoreCase(String nome);
	
	public Paciente findByEmailContainingIgnoreCase(String email);

	public Paciente findByCpf(String cpf);
	
}