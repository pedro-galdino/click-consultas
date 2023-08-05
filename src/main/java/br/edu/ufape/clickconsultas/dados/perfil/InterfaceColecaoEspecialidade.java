package br.edu.ufape.clickconsultas.dados.perfil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Especialidade;

@Repository
public interface InterfaceColecaoEspecialidade extends JpaRepository<Especialidade, Long> {

}