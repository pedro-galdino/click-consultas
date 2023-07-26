package br.edu.ufape.clickconsultas.negocios.modelo;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Consulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private Paciente paciente;
	@ManyToOne
	private Medico medico;
	@OneToOne
	private Agendamento agendamento;
	
	public Consulta() {
	}
	
	public Consulta(Paciente paciente, Medico medico, Agendamento agendamento) {
		super();
		this.paciente = paciente;
		this.medico = medico;
		this.agendamento = agendamento;
	}

	public long getId() {
		return id;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public Medico getMedico() {
		return medico;
	}
	
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public Agendamento getAgendamento() {
		return agendamento;
	}
	
	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	
}
