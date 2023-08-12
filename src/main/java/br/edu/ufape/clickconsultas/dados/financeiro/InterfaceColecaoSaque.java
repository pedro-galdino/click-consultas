package br.edu.ufape.clickconsultas.dados.financeiro;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Saque;

public interface InterfaceColecaoSaque extends JpaRepository<Saque, Long> {

	public List<Saque> findByData(Date data);

}
