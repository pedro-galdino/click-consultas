package br.edu.ufape.clickconsultas.negocios.modelo;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Saque implements Transacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private float valor;
	private Date data;
	private String banco;
	@ManyToOne(cascade = CascadeType.ALL)
	private Carteira carteira;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Pix chavePix;

	public Saque() {
	}

	public Saque(float valor, Date data, String banco, Carteira carteira, Pix chavePix) {
		this.valor = valor;
		this.data = data;
		this.banco = banco;
		this.carteira = carteira;
		this.chavePix = chavePix;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public Pix getChavePix() {
		return chavePix;
	}

	public void setChavePix(Pix chavePix) {
		this.chavePix = chavePix;
	}

	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}

	@Override
	public void processarTransacao() throws Exception {
		if (valor > 0) {
			if (valor <= carteira.getSaldo()) {
				carteira.setSaldo(carteira.getSaldo() - valor);
			} else {
				throw new Exception("Saldo insuficiente para realizar o saque.");
			}
		} else {
			throw new Exception("Valor para saque inválido.");
		}
	}

}
