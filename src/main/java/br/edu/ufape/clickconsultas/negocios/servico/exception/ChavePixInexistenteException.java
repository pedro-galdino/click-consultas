package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class ChavePixInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	private long id;
	
	public ChavePixInexistenteException(long id) {
		super("A chave pix não foi encontrada");
		this.id = id;
	}
	
	public long getPix() {
		return this.id;
	}
	
}
