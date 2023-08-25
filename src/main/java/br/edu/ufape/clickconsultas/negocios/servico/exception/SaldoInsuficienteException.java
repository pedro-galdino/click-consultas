package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class SaldoInsuficienteException extends Exception {
	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException() {
		super("Saldo da carteira insuficiente para transação.");
	}
}
