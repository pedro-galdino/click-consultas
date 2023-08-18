package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class UsuarioNaoEncontradoException extends Exception {
	private static final long serialVersionUID = 1L;
	private long id;

	public UsuarioNaoEncontradoException(long id, String tipo) {
		super("O " + tipo + " não foi encontrado.");
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

}
