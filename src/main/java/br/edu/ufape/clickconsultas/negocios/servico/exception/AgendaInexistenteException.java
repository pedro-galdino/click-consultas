package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class AgendaInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public AgendaInexistenteException() {
		super("A agenda inserida não foi encontrada");
	}
	
}