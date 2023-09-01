package br.edu.ufape.clickconsultas.dados.perfil;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Usuario;

@Repository
public interface InterfaceColecaoUsuario extends JpaRepository<Usuario, Long> {

	public List<Usuario> findByNomeIgnoreCase(String nome);
	
	public Usuario findByEmailIgnoreCase(String email);

	public Usuario findByCpf(String cpf);
	
}
