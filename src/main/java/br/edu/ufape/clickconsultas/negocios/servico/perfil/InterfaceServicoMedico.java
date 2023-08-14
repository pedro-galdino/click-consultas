package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioInexistenteException;

public interface InterfaceServicoMedico {

	List<Medico> buscarTodos();

	Medico buscarPorId(long id);

	List<Medico> buscarPorNome(String nome);

	Medico buscarPorEmail(String email) throws UsuarioInexistenteException;
	
	List<Medico> buscarPorEspecialidade(String nomeEspecialidade);
	
	Medico buscarPorCrm(int crm);

	Medico salvar(Medico medico);

	void remover(long id);

}