package br.edu.ufape.clickconsultas.negocios.modelo.financeiro;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Usuario;
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
	private double saldo;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Pix> chavesPix;
	@OneToOne
	private Usuario usuario;
	
	public Carteira() {
	}

	public Carteira(double saldo, List<Pix> chavesPix, Usuario usuario) {
		super();
		this.saldo = saldo;
		this.chavesPix = chavesPix;
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
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
	
	public void removerPix(Pix pix) {
        chavesPix.remove(pix);
    }
		
}
