package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class CpfExistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	private String cpf;
	
	public CpfExistenteException(String cpf) {
		super("O CPF inserido já está em uso.");
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
}