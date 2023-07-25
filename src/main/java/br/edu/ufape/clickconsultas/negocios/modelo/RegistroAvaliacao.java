package br.edu.ufape.clickconsultas.negocios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RegistroAvaliacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int numeroAvaliacoes;
	private int totalAvaliacoes;
	
	public RegistroAvaliacao() {}
	
	public RegistroAvaliacao(int numeroAvaliacoes, int totalAvaliacoes) {
		super();
		this.numeroAvaliacoes = numeroAvaliacoes;
		this.totalAvaliacoes = totalAvaliacoes;
	}

	public int getNumeroAvaliacoes() {
		return numeroAvaliacoes;
	}

	public void setNumeroAvaliacoes(int numeroAvaliacoes) {
		this.numeroAvaliacoes = numeroAvaliacoes;
	}

	public int getTotalAvaliacoes() {
		return totalAvaliacoes;
	}

	public void setTotalAvaliacoes(int totalAvaliacoes) {
		this.totalAvaliacoes = totalAvaliacoes;
	}

}
