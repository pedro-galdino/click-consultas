package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class DataDeAgendamentoInvalidaException extends Exception {
	private static final long serialVersionUID = 1L;

	public DataDeAgendamentoInvalidaException() {
		super("A data deve ser posterior à atual.");
	}

}
