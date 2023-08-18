package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CpfExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CrmInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EmailExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EspecialidadeInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioNaoEncontradoException;

public interface InterfaceServicoMedico {

	List<Medico> buscarTodos();

	Medico buscarPorId(long id) throws UsuarioNaoEncontradoException;

	List<Medico> buscarPorNome(String nome);
	
	Medico buscarPorCpf(String cpf) throws UsuarioInexistenteException;

	Medico buscarPorEmail(String email) throws UsuarioInexistenteException;
	
	List<Medico> buscarPorEspecialidade(String nomeEspecialidade) throws EspecialidadeInexistenteException;
	
	Medico buscarPorCrm(int crm) throws CrmInexistenteException;

	Medico salvar(Medico medico) throws EmailExistenteException, CpfExistenteException;

	void remover(long id) throws UsuarioNaoEncontradoException;

}