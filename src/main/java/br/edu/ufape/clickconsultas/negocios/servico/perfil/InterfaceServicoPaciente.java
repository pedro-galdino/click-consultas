package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CpfExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EmailExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioInexistenteException;

public interface InterfaceServicoPaciente {

	List<Paciente> buscarTodos();

	List<Paciente> buscarPorNome(String nome);

	Paciente buscarPorEmail(String email) throws UsuarioInexistenteException;

	Paciente buscarPorId(long id)  throws UsuarioInexistenteException;

	Paciente salvar(Paciente paciente) throws EmailExistenteException, CpfExistenteException;

	void remover(long id);

}