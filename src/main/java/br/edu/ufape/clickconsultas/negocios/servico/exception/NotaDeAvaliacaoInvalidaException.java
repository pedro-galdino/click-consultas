package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class NotaDeAvaliacaoInvalidaException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotaDeAvaliacaoInvalidaException(int min, int max) {
		super("A avaliação deve ter uma nota entre " + min + " e " + max + ".");
	}
}
