package br.edu.ufape.clickconsultas.negocios.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Horarios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate data;
	private List<LocalTime> horarios;
	
	public Horarios() {
	}
	
	public Horarios(LocalDate data, List<LocalTime> horarios) {
		this.data = data;
		this.horarios = horarios;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<LocalTime> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<LocalTime> horarios) {
		this.horarios = horarios;
	}
	
}
