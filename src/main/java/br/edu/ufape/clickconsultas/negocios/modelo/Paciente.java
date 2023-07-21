package br.edu.ufape.clickconsultas.negocios.modelo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Paciente extends Usuario {
    private String cidade;
    private String estado;
    @OneToOne
    private PlanoSaude plano;
    
    public Paciente(long id, String nome, String cpf, Date dataNascimento, String sexo,
            String telefone, String email, String senha, String cidade, String estado, PlanoSaude plano) {
		super(id, nome, cpf, dataNascimento, sexo, telefone, email, senha);
		this.cidade = cidade;
		this.estado = estado;
		this.plano = plano;
	}
    
    public String getCidade() {
        return cidade;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public PlanoSaude getPlano() {
        return plano;
    }
    
    public void setPlano(PlanoSaude plano) {
        this.plano = plano;
    }
    
    
    
}
