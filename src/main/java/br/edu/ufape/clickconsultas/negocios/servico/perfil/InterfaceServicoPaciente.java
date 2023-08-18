package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CpfExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EmailExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioNaoEncontradoException;

public interface InterfaceServicoPaciente {

	List<Paciente> buscarTodos();

	List<Paciente> buscarPorNome(String nome);
	
	Paciente buscarPorCpf(String cpf) throws UsuarioInexistenteException;

	Paciente buscarPorEmail(String email) throws UsuarioInexistenteException;

	Paciente buscarPorId(long id) throws UsuarioNaoEncontradoException;

	Paciente salvar(Paciente paciente) throws EmailExistenteException, CpfExistenteException;

	void remover(long id) throws UsuarioNaoEncontradoException;

}