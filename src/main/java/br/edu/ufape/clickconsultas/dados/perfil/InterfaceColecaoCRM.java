package br.edu.ufape.clickconsultas.dados.perfil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;

@Repository
public interface InterfaceColecaoCRM extends JpaRepository<CRM, Long> {
	public CRM findByNumeroAndUF(int numero, String UF);
}