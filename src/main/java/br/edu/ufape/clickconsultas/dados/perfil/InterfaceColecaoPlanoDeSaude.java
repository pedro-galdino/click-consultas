package br.edu.ufape.clickconsultas.dados.perfil;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.PlanoDeSaude;

@Repository
public interface InterfaceColecaoPlanoDeSaude extends JpaRepository<PlanoDeSaude, Long> {
	public List<PlanoDeSaude> findByOperadora(String operadora);
	public PlanoDeSaude findByNumero(int numero);
}
