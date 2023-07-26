package br.edu.ufape.clickconsultas.dados.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Deposito;

public interface InterfaceColecaoDeposito extends JpaRepository<Deposito, Long> {

}
