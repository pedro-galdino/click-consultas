package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class EspecialidadesExcedidasException extends Exception {
	private static final long serialVersionUID = 1L;

	public EspecialidadesExcedidasException() {
		super("Não é possível cadastrar mais do que duas especialidades.");
	}
}
