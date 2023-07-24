package br.edu.ufape.clickconsultas.dados;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufape.clickconsultas.negocios.modelo.Saque;

public interface InterfaceColecaoSaque extends JpaRepository<Saque, Long> {

}
