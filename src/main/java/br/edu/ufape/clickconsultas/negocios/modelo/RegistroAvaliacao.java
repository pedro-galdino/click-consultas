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
	private double totalAvaliacoes;

	public RegistroAvaliacao() {
	}

	public RegistroAvaliacao(int numeroAvaliacoes, double totalAvaliacoes) {
		this.numeroAvaliacoes = numeroAvaliacoes;
		this.totalAvaliacoes = totalAvaliacoes;
	}

	public long getId() {
		return id;
	}

	public int getNumeroAvaliacoes() {
		return numeroAvaliacoes;
	}

	public void setNumeroAvaliacoes(int numeroAvaliacoes) {
		this.numeroAvaliacoes = numeroAvaliacoes;
	}

	public double getTotalAvaliacoes() {
		return totalAvaliacoes;
	}

	public void setTotalAvaliacoes(double totalAvaliacoes) {
		this.totalAvaliacoes = totalAvaliacoes;
	}

	public void adicionarNota(double nota) {
		numeroAvaliacoes++;
		totalAvaliacoes += nota;
	}

	public void removerNota(double nota) {
		numeroAvaliacoes--;
		totalAvaliacoes -= nota;
	}

	public double calcularMediaAvaliacoes() {
		return totalAvaliacoes / numeroAvaliacoes;
	}

}