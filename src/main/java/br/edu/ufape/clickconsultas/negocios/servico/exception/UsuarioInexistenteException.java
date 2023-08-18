package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class UsuarioInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	private String email;
	
	public UsuarioInexistenteException(String email) {
		super("O e-mail inserido não foi encontrado.");
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

}