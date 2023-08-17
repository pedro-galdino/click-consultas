package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class UsuarioInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public UsuarioInexistenteException() {
		super("O usuario inserido não foi encontrado");
	}
	
}