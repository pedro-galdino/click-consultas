package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class ObjetoNaoEncontradoException extends Exception {
	private static final long serialVersionUID = 1L;

	public ObjetoNaoEncontradoException(String artigo, String nome) {
		super(artigo.toUpperCase() + " " + nome + " não foi encontrad" + artigo + ".");
	}

}