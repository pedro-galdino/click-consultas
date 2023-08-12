package br.edu.ufape.clickconsultas.dados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.EnderecoMedico;

@Repository
public interface InterfaceColecaoEnderecoMedico extends JpaRepository<EnderecoMedico, Long> {
	
	public List<EnderecoMedico> findByEstado(String estado);

}
