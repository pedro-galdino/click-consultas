package br.edu.ufape.clickconsultas.negocios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CRM {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String uf;
	private int numero;
	
	public CRM(long id, String uf, int numero) {
		this.id = id;
		this.uf = uf;
		this.numero = numero;
	}
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}
