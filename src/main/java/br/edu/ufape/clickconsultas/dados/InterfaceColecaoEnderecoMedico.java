package br.edu.ufape.clickconsultas.dados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.EnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;

@Repository
public interface InterfaceColecaoEnderecoMedico extends JpaRepository<EnderecoMedico, Long> {

	public List<EnderecoMedico> findByCidade(String cidade);

	public List<EnderecoMedico> findByEstado(String estado);

	public List<EnderecoMedico> findByCep(String cep);

	public List<EnderecoMedico> findByMedico(Medico medico);

}
