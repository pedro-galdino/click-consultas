package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class CrmExistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	private String crm;
	
	public CrmExistenteException(String crm) {
		super("O CRM inserido já está em uso");
		this.crm = crm;
	}
	
	public String getCrm() {
		return this.crm;
	}
	
}