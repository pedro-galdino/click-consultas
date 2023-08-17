package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CpfExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CrmExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CrmInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EmailExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EspecialidadeInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioInexistenteException;

public interface InterfaceServicoMedico {

	List<Medico> buscarTodos();

	Medico buscarPorId(long id) throws UsuarioInexistenteException ;

	List<Medico> buscarPorNome(String nome);

	Medico buscarPorEmail(String email) throws UsuarioInexistenteException;
	
	List<Medico> buscarPorEspecialidade(String nomeEspecialidade) throws EspecialidadeInexistenteException;
	
	Medico buscarPorCrm(int crm) throws CrmInexistenteException;

	Medico salvar(Medico medico) throws EmailExistenteException, CrmExistenteException, CpfExistenteException;

	void remover(long id);

}