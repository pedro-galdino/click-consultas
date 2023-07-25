package br.edu.ufape.clickconsultas.negocios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Agendamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private EnderecoMedico localConsulta;
	private String planoAtendido;
	private float valorFinalConsulta;
	private String detalhamento;
	
	public Agendamento(){}
	
	public Agendamento(int id, EnderecoMedico localConsulta, String planoAtendido, float valorFinalConsulta,
			String detalhamento) {
		super();
		this.id = id;
		this.localConsulta = localConsulta;
		this.planoAtendido = planoAtendido;
		this.valorFinalConsulta = valorFinalConsulta;
		this.detalhamento = detalhamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EnderecoMedico getLocalConsulta() {
		return localConsulta;
	}

	public void setLocalConsulta(EnderecoMedico localConsulta) {
		this.localConsulta = localConsulta;
	}

	public String getPlanoAtendido() {
		return planoAtendido;
	}

	public void setPlanoAtendido(String planoAtendido) {
		this.planoAtendido = planoAtendido;
	}

	public float getValorFinalConsulta() {
		return valorFinalConsulta;
	}

	public void setValorFinalConsulta(float valorFinalConsulta) {
		this.valorFinalConsulta = valorFinalConsulta;
	}

	public String getDetalhamento() {
		return detalhamento;
	}

	public void setDetalhamento(String detalhamento) {
		this.detalhamento = detalhamento;
	}
	
}
