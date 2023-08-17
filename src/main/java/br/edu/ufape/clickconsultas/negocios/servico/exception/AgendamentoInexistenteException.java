package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class AgendamentoInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public AgendamentoInexistenteException() {
		super("A agenda inserida não foi encontrada");
	}
	
}