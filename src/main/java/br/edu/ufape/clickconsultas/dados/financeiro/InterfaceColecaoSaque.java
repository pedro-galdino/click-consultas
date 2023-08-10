package br.edu.ufape.clickconsultas.dados.financeiro;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Saque;

public interface InterfaceColecaoSaque extends JpaRepository<Saque, Long> {

	public List<Saque> findByCarteira(Carteira carteira);

	public List<Saque> findByDate(Date data);

	public List<Saque> findByBanco(String banco);

}
