package br.edu.ufape.clickconsultas.negocios.modelo.financeiro;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Deposito implements Transacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double valor;
	private Date data;
	private String chavePix;
	@ManyToOne
	private Carteira carteira;

	public Deposito() {
	}

	public Deposito(double valor, Date data, String chavePix, Carteira carteira) {
		this.valor = valor;
		this.data = data;
		this.chavePix = chavePix;
		this.carteira = carteira;
	}

	public long getId() {
		return id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getChavePix() {
		return chavePix;
	}

	public void setChavePix(String chavePix) {
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
			carteira.setSaldo(carteira.getSaldo() + valor);
		} else {
			throw new Exception("Valor para depósito inválido.");
		}
	}

	public void gerarPixCopiaECola() {

	}

}
