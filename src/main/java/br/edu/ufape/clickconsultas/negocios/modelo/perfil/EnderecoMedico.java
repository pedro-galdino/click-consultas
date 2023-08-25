package br.edu.ufape.clickconsultas.negocios.modelo.perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EnderecoMedico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String cidade;
	private String estado;
	private String cep;
	private String bairro;
	private String logradouro;
	private int numero;
	private String detalhamento;

	public EnderecoMedico() {
	}

	public EnderecoMedico(String cidade, String estado, String cep, String bairro, String logradouro, int numero, String detalhamento) {
		super();
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.bairro = bairro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.detalhamento = detalhamento;
	}

	public long getId() {
		return id;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDetalhamento() {
		return detalhamento;
	}

	public void setDetalhamento(String detalhamento) {
		this.detalhamento = detalhamento;
	}

}
