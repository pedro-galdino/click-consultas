package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class ObjetoRegistradoException extends Exception {
	private static final long serialVersionUID = 1L;

	public ObjetoRegistradoException(String artigo, String nome) {
		super(artigo.toUpperCase() + " " + nome + " já foi registrada.");
	}

}
