package br.edu.ufape.clickconsultas.dados;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.EnderecoMedico;

@Repository
public interface InterfaceColecaoEnderecoMedico extends JpaRepository<EnderecoMedico, Long> {

}
