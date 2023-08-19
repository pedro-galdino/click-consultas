package br.edu.ufape.clickconsultas.negocios.modelo.perfil;

import java.time.LocalDate;
import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.RegistroAvaliacao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Medico extends Usuario {
	private String foto;
	@OneToMany(cascade = CascadeType.ALL)
	private List<CRM> crm;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Especialidade> especialidades;
	@OneToOne(cascade = CascadeType.ALL)
	private RegistroAvaliacao registroAvaliacao;

	public Medico() {
	}

	public Medico(String nome, String cpf, LocalDate dataNascimento, String sexo, String telefone, String email,
			String senha, String foto, List<CRM> crm, List<Especialidade> especialidades, RegistroAvaliacao registroAvaliacao) {
		super(nome, cpf, dataNascimento, sexo, telefone, email, senha);
		this.foto = foto;
		this.crm = crm;
		this.especialidades = especialidades;
		this.registroAvaliacao = registroAvaliacao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<CRM> getCrm() {
		return crm;
	}

	public void setCrm(List<CRM> crm) {
		this.crm = crm;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public RegistroAvaliacao getRegistroAvaliacao() {
		return registroAvaliacao;
	}

	public void setRegistroAvaliacao(RegistroAvaliacao registroAvaliacao) {
		this.registroAvaliacao = registroAvaliacao;
	}
	
	public void removerCrm(CRM crm) {
		this.crm.remove(crm);
    }
	
	public void removerEspecialidade(Especialidade especialidade) {
        especialidades.remove(especialidade);
    }
	
	
}