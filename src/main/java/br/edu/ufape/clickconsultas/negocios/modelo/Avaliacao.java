package br.edu.ufape.clickconsultas.negocios.modelo;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Avaliacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double nota;
	private String comentario;
	@ManyToOne
	private Paciente paciente;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private RegistroAvaliacao registro;

	public Avaliacao() {
	}

	public Avaliacao(double nota, String comentario, Paciente paciente, RegistroAvaliacao registro) {
		super();
		this.nota = nota;
		this.comentario = comentario;
		this.paciente = paciente;
		this.registro = registro;
	}

	public long getId() {
		return id;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public RegistroAvaliacao getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroAvaliacao registro) {
		this.registro = registro;
	}

}
