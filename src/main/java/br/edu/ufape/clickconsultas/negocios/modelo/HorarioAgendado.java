package br.edu.ufape.clickconsultas.negocios.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HorarioAgendado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate data;
	private LocalTime hora;
	
	public HorarioAgendado() {
	}
	
	public HorarioAgendado(LocalDate data, LocalTime hora) {
		this.data = data;
		this.hora = hora;
	}

	public long getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	
}
