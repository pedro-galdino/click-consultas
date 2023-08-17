package br.edu.ufape.clickconsultas.negocios.modelo;

import java.lang.reflect.Field;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Agendamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private EnderecoMedico localConsulta;
	private String tipoConsulta;
	private String planoAtendido;
	private double valorFinalConsulta;
	private String detalhamento;
	@ManyToOne(cascade = CascadeType.ALL)
	private HorarioAgendado horarioAgendado;
	@ManyToOne
	private Paciente paciente;
	@ManyToOne
	private Agenda agenda;
	
	public Agendamento() {
	}

	public Agendamento(EnderecoMedico localConsulta, String tipoConsulta, String planoAtendido, double valorFinalConsulta, 
			String detalhamento, HorarioAgendado horarioAgendado, Paciente paciente, Agenda agenda) {
		this.localConsulta = localConsulta;
		this.tipoConsulta = tipoConsulta;
		this.planoAtendido = planoAtendido;
		this.valorFinalConsulta = valorFinalConsulta;
		this.detalhamento = detalhamento;
		this.horarioAgendado = horarioAgendado;
		this.paciente = paciente;
		this.agenda = agenda;
	}

	public long getId() {
		return id;
	}

	public EnderecoMedico getLocalConsulta() {
		return localConsulta;
	}

	public void setLocalConsulta(EnderecoMedico localConsulta) {
		this.localConsulta = localConsulta;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public String getPlanoAtendido() {
		return planoAtendido;
	}

	public void setPlanoAtendido(String planoAtendido) {
		this.planoAtendido = planoAtendido;
	}

	public double getValorFinalConsulta() {
		return valorFinalConsulta;
	}

	public void setValorFinalConsulta(double valorFinalConsulta) {
		this.valorFinalConsulta = valorFinalConsulta;
	}

	public String getDetalhamento() {
		return detalhamento;
	}

	public void setDetalhamento(String detalhamento) {
		this.detalhamento = detalhamento;
	}

	public HorarioAgendado getHorarioAgendado() {
		return horarioAgendado;
	}

	public void setHorarioAgendado(HorarioAgendado horarioAgendado) {
		this.horarioAgendado = horarioAgendado;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
	public boolean verificarAtributos() {
		Field[]atributos = this.getClass().getDeclaredFields();
		for(Field atributo : atributos) {
			atributo.setAccessible(true);
			try {
				if(atributo.get(this) == null) {
					return true;
				}
			}
			catch (IllegalAccessException e) {
				
			}
		}
		return false;
	}
	
}
