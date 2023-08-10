package br.edu.ufape.clickconsultas.dados.financeiro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;

@Repository
public interface InterfaceColecaoPix extends JpaRepository<Pix, Long> {

	public List<Pix> findByTipo(String tipo);

}
