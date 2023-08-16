package br.edu.ufape.clickconsultas.dados.perfil;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;

@Repository
public interface InterfaceColecaoMedico extends JpaRepository<Medico, Long> {
	
	public List<Medico> findByNome(String nome);
	
	public List<Medico> findByEspecialidadesNome(String nomeEspecialidade);
	
	public Medico findByCrmNumero(int numeroCrm);
	
	public Medico findByEmail(String email);

	public Medico findByCpf(String cpf);
	
}