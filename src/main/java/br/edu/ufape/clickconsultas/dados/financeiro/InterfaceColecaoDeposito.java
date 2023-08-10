package br.edu.ufape.clickconsultas.dados.financeiro;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Deposito;

public interface InterfaceColecaoDeposito extends JpaRepository<Deposito, Long> {

	public List<Deposito> findByCarteira(Carteira carteira);

	public List<Deposito> findByData(Date data);

}
