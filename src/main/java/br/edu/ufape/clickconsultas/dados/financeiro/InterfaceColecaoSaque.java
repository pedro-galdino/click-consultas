package br.edu.ufape.clickconsultas.dados.financeiro;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Saque;

@Repository
public interface InterfaceColecaoSaque extends JpaRepository<Saque, Long> {
	
	public List<Saque> findByData(Date data);

	public List<Saque> findByCarteiraId(Long carteiraId);
	
}
