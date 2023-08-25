package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class ListaVaziaException extends Exception {
	private static final long serialVersionUID = 1L;

	public ListaVaziaException(String nome) {
		super("A lista de " + nome + " está vazia.");
	}
}
