package br.edu.ufape.clickconsultas.negocios.modelo.financeiro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pix {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String chave;
	private String tipo;
	
	public Pix() {
	}
	
	public Pix(String chave, String tipo) {
		this.chave = chave;
		this.tipo = tipo;
	}

	public long getId() {
		return id;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
