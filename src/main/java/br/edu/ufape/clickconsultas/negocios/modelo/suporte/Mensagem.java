package br.edu.ufape.clickconsultas.negocios.modelo.suporte;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mensagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long idAutor;
	private String autor;
	private String texto;
	private LocalDateTime datahora;
	
	public Mensagem() {
	}
	
	public Mensagem(long idAutor, String autor, String texto, LocalDateTime datahora) {
		this.idAutor = idAutor;
		this.autor = autor;
		this.texto = texto;
		this.datahora = datahora;
	}

	public long getId() {
		return id;
	}

	public long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(long idAutor) {
		this.idAutor = idAutor;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getDatahora() {
		return datahora;
	}

	public void setDatahora(LocalDateTime datahora) {
		this.datahora = datahora;
	}
	
}
