package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class SenhaIncorretaException extends Exception {
	private static final long serialVersionUID = 1L;

	public SenhaIncorretaException() {
		super("A senha inserida está incorreta.");
	}
}
