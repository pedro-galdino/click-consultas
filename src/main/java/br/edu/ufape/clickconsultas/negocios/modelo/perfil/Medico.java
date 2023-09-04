package br.edu.ufape.clickconsultas.negocios.modelo.perfil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.RegistroAvaliacao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Medico extends Usuario {
	private String foto;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CRM> crm = new ArrayList<CRM>();;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Especialidade> especialidades = new ArrayList<Especialidade>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EnderecoMedico> enderecos = new ArrayList<EnderecoMedico>();
	@OneToOne
	private RegistroAvaliacao registroAvaliacao;

	public Medico() {
	}

	public Medico(String nome, String cpf, LocalDate dataNascimento, String sexo, String telefone, String email,
			String senha, String foto, List<CRM> crm, List<Especialidade> especialidades, List<EnderecoMedico> enderecos) {
		super(nome, cpf, dataNascimento, sexo, telefone, email, senha);
		this.foto = foto;
		this.crm = crm;
		this.especialidades = especialidades;
		this.enderecos = enderecos;
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

	public List<EnderecoMedico> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoMedico> enderecos) {
		this.enderecos = enderecos;
	}

	public void adicionarCrm(CRM crm) {
		this.crm.add(crm);
	}

	public void removerCrm(CRM crm) {
		this.crm.remove(crm);
	}

	public void adicionarEspecialidade(Especialidade especialidade) {
		this.especialidades.add(especialidade);
	}

	public void removerEspecialidade(Especialidade especialidade) {
		this.especialidades.remove(especialidade);
	}

	public void adicionarEndereco(EnderecoMedico endereco) {
		this.enderecos.add(endereco);
	}

	public void removerEndereco(EnderecoMedico endereco) {
		this.enderecos.remove(endereco);
	}

}