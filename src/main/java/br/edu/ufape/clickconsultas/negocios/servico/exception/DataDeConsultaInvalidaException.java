package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class DataDeConsultaInvalidaException extends Exception {
	private static final long serialVersionUID = 1L;

	public DataDeConsultaInvalidaException() {
		super("Não é possível concluir uma consulta com data posterior à atual.");
	}
}
