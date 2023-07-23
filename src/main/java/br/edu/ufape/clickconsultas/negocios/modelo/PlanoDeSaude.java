package br.edu.ufape.clickconsultas.negocios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PlanoDeSaude {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int numero;
	private String operadora;

	public PlanoDeSaude() {
	}

	public PlanoDeSaude(int numero, String operadora) {
		this.numero = numero;
		this.operadora = operadora;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

}
