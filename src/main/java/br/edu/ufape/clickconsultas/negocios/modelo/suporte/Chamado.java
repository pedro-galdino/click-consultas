package br.edu.ufape.clickconsultas.negocios.modelo.suporte;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Chamado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long protocolo;
	private String titulo;
	private String status;
	private LocalDateTime datahora;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Administrador admin;
	@OneToMany
	private List<Mensagem> mensagens;
	
	public Chamado() {
	}
	
	public Chamado(String titulo, String status, LocalDateTime datahora, Usuario usuario, Administrador admin, List<Mensagem> mensagens) {
		this.titulo = titulo;
		this.status = status;
		this.datahora = datahora;
		this.usuario = usuario;
		this.admin = admin;
		this.mensagens = mensagens;
	}

	public long getProtocolo() {
		return protocolo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
	
}
