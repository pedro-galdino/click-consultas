package br.edu.ufape.clickconsultas.negocios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Medico extends Usuario {
	@OneToMany
	private CRM crm[];
	@OneToMany
	private Especialidade especialidades[];
	private String foto;
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
}
