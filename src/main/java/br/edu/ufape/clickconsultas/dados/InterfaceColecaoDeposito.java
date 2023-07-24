package br.edu.ufape.clickconsultas.dados;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufape.clickconsultas.negocios.modelo.Deposito;

public interface InterfaceColecaoDeposito extends JpaRepository<Deposito, Long> {

}
