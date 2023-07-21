package br.edu.ufape.clickconsultas.negocios.modelo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Especialidade {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String nome;
	private int numeroRQE;
	
	public Especialidade(long id, String nome, int numeroRQE) {
		this.id = id;
		this.nome = nome;
		this.numeroRQE = numeroRQE;
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
