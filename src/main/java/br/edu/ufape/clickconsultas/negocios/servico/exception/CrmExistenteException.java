package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class CrmExistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	private int numeroCrm;
	
	public CrmExistenteException(int numeroCrm) {
		super("O CRM inserido já está em uso.");
		this.numeroCrm = numeroCrm;
	}
	
	public int getCrm() {
		return this.numeroCrm;
	}
	
}