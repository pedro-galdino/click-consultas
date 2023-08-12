package br.edu.ufape.clickconsultas.dados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.RegistroAvaliacao;

@Repository
public interface InterfaceColecaoRegistroAvaliacao extends JpaRepository<RegistroAvaliacao, Long> {

	public List<RegistroAvaliacao> findByNumeroAvaliacoes(int numeroAvaliacoes);

}