package br.edu.ufape.clickconsultas.negocios.modelo.perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Especialidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private int numeroRQE;

	public Especialidade() {
	}

	public Especialidade(String nome, int numeroRQE) {
		nome = formatar(capitalizarNome(nome));
		this.nome = nome;
		this.numeroRQE = numeroRQE;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		nome = formatar(capitalizarNome(nome));
		this.nome = nome;
	}

	public int getNumeroRQE() {
		return numeroRQE;
	}

	public void setNumeroRQE(int numeroRQE) {
		this.numeroRQE = numeroRQE;
	}
	
	public static String capitalizarNome(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
	
	public static String formatar(String input) {
		if(input == null || input.isEmpty()) {
			return input;
		}
        input = input.trim();
        input = input.replaceAll("\\s+", " ");
        return input;
	}

}
