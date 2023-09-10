package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class DataInvalidaException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public DataInvalidaException() {
		super("A data deve ser posterior à atual.");
	}

}
