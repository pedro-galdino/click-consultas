package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class EmailExistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	private String email;
	
	public EmailExistenteException(String email) {
		super("O e-mail inserido já está em uso.");
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
}
