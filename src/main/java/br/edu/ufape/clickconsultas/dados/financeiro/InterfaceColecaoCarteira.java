package br.edu.ufape.clickconsultas.dados.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Usuario;

@Repository
public interface InterfaceColecaoCarteira extends JpaRepository<Carteira, Long> {

	public Carteira findByUsuario(Usuario usuario);

}
