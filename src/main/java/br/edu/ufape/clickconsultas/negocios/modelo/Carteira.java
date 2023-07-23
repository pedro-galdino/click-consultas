package br.edu.ufape.clickconsultas.negocios.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Carteira {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private float saldo;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Pix> chavesPix;
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
	
	public Carteira() {
	}

	public Carteira(float saldo, List<Pix> chavesPix, Usuario usuario) {
		super();
		this.saldo = saldo;
		this.chavesPix = chavesPix;
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public List<Pix> getChavesPix() {
		return chavesPix;
	}

	public void setChavesPix(List<Pix> chavesPix) {
		this.chavesPix = chavesPix;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
		
}
