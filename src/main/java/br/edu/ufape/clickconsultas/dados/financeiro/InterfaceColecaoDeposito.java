package br.edu.ufape.clickconsultas.dados.financeiro;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Deposito;

@Repository
public interface InterfaceColecaoDeposito extends JpaRepository<Deposito, Long> {
	
	public List<Deposito> findByData(Date data);
	
	public List<Deposito> findByCarteiraId(Long carteiraId);

}
