package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class ChavePixInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	private String chavePix;
	
	public ChavePixInexistenteException(String chavePix) {
		super("A chave pix não foi encontrada");
		this.chavePix = chavePix;
	}
	
	public String getPix() {
		return this.chavePix;
	}
	
}
