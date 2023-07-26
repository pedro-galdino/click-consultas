package br.edu.ufape.clickconsultas.negocios.modelo.perfil;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Paciente extends Usuario {
	private String cidade;
	private String estado;
	@OneToOne(cascade = CascadeType.ALL)
	private PlanoDeSaude plano;

	public Paciente() {
	}

	public Paciente(String nome, String cpf, LocalDate dataNascimento, String sexo, String telefone, String email,
			String senha, String cidade, String estado, PlanoDeSaude plano) {
		super(nome, cpf, dataNascimento, sexo, telefone, email, senha);
		this.cidade = cidade;
		this.estado = estado;
		this.plano = plano;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public PlanoDeSaude getPlano() {
		return plano;
	}

	public void setPlano(PlanoDeSaude plano) {
		this.plano = plano;
	}

}
