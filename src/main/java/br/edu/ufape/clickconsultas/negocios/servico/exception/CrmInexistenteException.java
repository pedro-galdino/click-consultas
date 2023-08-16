package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class CrmInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	private int crm;
	
	public CrmInexistenteException(int crm) {
		super("O CRM inserido não foi encontrado");
		this.crm = crm;
	}
	
	public int getCrm() {
		return this.crm;
	}
}
