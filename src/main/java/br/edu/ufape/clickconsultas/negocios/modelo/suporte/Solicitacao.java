package br.edu.ufape.clickconsultas.negocios.modelo.suporte;

import java.time.LocalDateTime;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Especialidade;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Solicitacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long protocolo;
	private String tipo;
	private String status;
	private LocalDateTime datahora;
	@ManyToOne
	private Medico medico;
	@ManyToOne
	private Administrador admin;
	@OneToOne
	private CRM crm;
	@OneToOne
	private Especialidade especialidade;

	public Solicitacao() {
	}

	public Solicitacao(String tipo, String status, LocalDateTime datahora, Medico medico, Administrador admin, CRM crm,
			Especialidade especialidade) {
		this.tipo = tipo;
		this.status = status;
		this.datahora = datahora;
		this.medico = medico;
		this.admin = admin;
		this.crm = crm;
		this.especialidade = especialidade;
	}

	public long getProtocolo() {
		return protocolo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDatahora() {
		return datahora;
	}

	public void setDatahora(LocalDateTime datahora) {
		this.datahora = datahora;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public CRM getCrm() {
		return crm;
	}

	public void setCrm(CRM crm) {
		this.crm = crm;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

}
