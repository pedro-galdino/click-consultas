package br.edu.ufape.clickconsultas.negocios.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private String sexo;
	private String telefone;
	private String email;
	private String senha;

	public Usuario() {
	}

	public Usuario(String nome, String cpf, LocalDate dataNascimento, String sexo, String telefone, String email, String senha) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void atualizarPerfil(String nome, String cpf, LocalDate dataNascimento, String sexo, String telefone,
			String email, String senha) {
		if (nome != this.nome) {
			this.nome = nome;
		}
		if (cpf != this.cpf) {
			this.cpf = cpf;
		}
		if (dataNascimento != this.dataNascimento) {
			this.dataNascimento = dataNascimento;
		}
		if (sexo != this.sexo) {
			this.sexo = sexo;
		}
		if (telefone != this.telefone) {
			this.telefone = telefone;
		}
		if (email != this.email) {
			this.email = email;
		}
		if (senha != this.senha) {
			this.senha = senha;
		}
	}

}
