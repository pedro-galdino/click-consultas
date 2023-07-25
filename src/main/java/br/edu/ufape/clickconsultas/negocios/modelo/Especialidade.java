package br.edu.ufape.clickconsultas.negocios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Especialidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private int numeroRQE;

	public Especialidade() {
	}

	public Especialidade(String nome, int numeroRQE) {
		this.nome = nome;
		this.numeroRQE = numeroRQE;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumeroRQE() {
		return numeroRQE;
	}

	public void setNumeroRQE(int numeroRQE) {
		this.numeroRQE = numeroRQE;
	}

}
