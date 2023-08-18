package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class ObjetoEmUsoException extends Exception {
	private static final long serialVersionUID = 1L;

	public ObjetoEmUsoException(String artigo, String nome) {
		super(artigo.toUpperCase() + " " + nome + " inserid" + artigo + " já está em uso.");
	}

}