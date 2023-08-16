package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class CrmInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	private String crm;
	
	public CrmInexistenteException(String crm) {
		super("O CRM inserido não foi encontrado");
		this.crm = crm;
	}
	
	public String getCrm() {
		return this.crm;
	}
}
