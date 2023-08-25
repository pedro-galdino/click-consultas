package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class DadosInsuficientesException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public DadosInsuficientesException() {
		super("Dados insuficientes para cadastro.");
	}
}
