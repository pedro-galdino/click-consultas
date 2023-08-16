package br.edu.ufape.clickconsultas.negocios.servico.exception;

public class EspecialidadeInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	private String nomeEspecialidade;
	
	public EspecialidadeInexistenteException(String nomeEspecialidade) {
		super("A especialidade não foi encontrada");
		this.nomeEspecialidade = nomeEspecialidade;
	}
	
	public String getPix() {
		return this.nomeEspecialidade;
	}
	
}
