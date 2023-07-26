package br.edu.ufape.clickconsultas.dados;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.RegistroAvaliacao;

@Repository
public interface InterfaceColecaoRegistroAvaliacao extends JpaRepository<RegistroAvaliacao, Long> {

}