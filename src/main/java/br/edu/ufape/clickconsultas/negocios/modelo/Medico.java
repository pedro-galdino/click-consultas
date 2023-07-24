package br.edu.ufape.clickconsultas.negocios.modelo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Medico extends Usuario {
	private String foto;
	@OneToMany(cascade = CascadeType.ALL)
	private List<CRM> crm;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Especialidade> especialidades;

	public Medico() {
	}

	public Medico(String nome, String cpf, LocalDate dataNascimento, String sexo, String telefone, String email,
			String senha, String foto, List<CRM> crm, List<Especialidade> especialidades) {
		super(nome, cpf, dataNascimento, sexo, telefone, email, senha);
		this.foto = foto;
		this.crm = crm;
		this.especialidades = especialidades;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<CRM> getCrm() {
		return crm;
	}

	public void setCrm(List<CRM> crm) {
		this.crm = crm;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}
	
}
