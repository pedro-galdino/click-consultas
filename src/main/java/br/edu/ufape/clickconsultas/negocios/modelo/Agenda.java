package br.edu.ufape.clickconsultas.negocios.modelo;

import java.lang.reflect.Field;
import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.EnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Agenda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String especialidadeMedica;
	private List<String> tiposConsulta;
	private List<String> planosAtendidos;
	private double valorConsulta;
	private String contato;
	@ManyToOne
	private EnderecoMedico enderecoMedico;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Horarios> horariosDisponiveis;
	@OneToMany
	private List<HorarioAgendado> horariosAgendados;
	@ManyToOne
	private Medico medico;

	public Agenda() {
	}

	public Agenda(String especialidadeMedica, List<String> tiposConsulta, List<String> planosAtendidos,
			double valorConsulta, String contato, EnderecoMedico enderecoMedico,
			List<Horarios> horariosDisponiveis, List<HorarioAgendado> horariosAgendados, Medico medico) {
		this.especialidadeMedica = especialidadeMedica;
		this.tiposConsulta = tiposConsulta;
		this.planosAtendidos = planosAtendidos;
		this.valorConsulta = valorConsulta;
		this.contato = contato;
		this.enderecoMedico = enderecoMedico;
		this.horariosDisponiveis = horariosDisponiveis;
		this.horariosAgendados = horariosAgendados;
		this.medico = medico;
	}

	public long getId() {
		return id;
	}

	public String getEspecialidadeMedica() {
		return especialidadeMedica;
	}

	public void setEspecialidadeMedica(String especialidadeMedica) {
		this.especialidadeMedica = especialidadeMedica;
	}

	public List<String> getTiposConsulta() {
		return tiposConsulta;
	}

	public void setTiposConsulta(List<String> tiposConsulta) {
		this.tiposConsulta = tiposConsulta;
	}

	public List<String> getPlanosAtendidos() {
		return planosAtendidos;
	}

	public void setPlanosAtendidos(List<String> planosAtendidos) {
		this.planosAtendidos = planosAtendidos;
	}

	public double getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(double valorConsulta) {
		this.valorConsulta = valorConsulta;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public EnderecoMedico getEnderecoMedico() {
		return enderecoMedico;
	}

	public void setEnderecoMedico(EnderecoMedico enderecoMedico) {
		this.enderecoMedico = enderecoMedico;
	}

	public List<Horarios> getHorariosDisponiveis() {
		return horariosDisponiveis;
	}

	public void setHorariosDisponiveis(List<Horarios> horariosDisponiveis) {
		this.horariosDisponiveis = horariosDisponiveis;
	}

	public List<HorarioAgendado> getHorariosAgendados() {
		return horariosAgendados;
	}

	public void setHorariosAgendados(List<HorarioAgendado> horariosAgendados) {
		this.horariosAgendados = horariosAgendados;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public boolean verificarAtributos() {
		Field[] atributos = this.getClass().getDeclaredFields();
		for (Field atributo : atributos) {
			atributo.setAccessible(true);
			try {
				if (atributo.get(this) == null) {
					return true;
				}
			} catch (IllegalAccessException e) {

			}
		}
		return false;
	}

}