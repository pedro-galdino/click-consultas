package br.edu.ufape.clickconsultas.negocios.modelo.perfil;

import java.time.LocalDate;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private String sexo;
	private String telefone;
	private String email;
	private String senha;
	@OneToOne(cascade = CascadeType.ALL)
	private Carteira carteira;

	public Usuario() {
	}

	public Usuario(String nome, String cpf, LocalDate dataNascimento, String sexo, String telefone, String email, String senha) {
		super();
		this.nome = formatar(capitalizarNome(nome));
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.telefone = telefone;
		this.email = formatar(email).toLowerCase();
		this.senha = senha;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = formatar(capitalizarNome(nome));
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = formatar(email).toLowerCase();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}
	
	public static String capitalizarNome(String input) {
		if (input == null || input.isEmpty()) {
            return input;
        }
        
        input = formatar(input); // Remover espaços extras e formatar
        
        String[] palavras = input.split(" ");
        StringBuilder resultado = new StringBuilder();
        
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                resultado.append(palavra.substring(0, 1).toUpperCase());
                resultado.append(palavra.substring(1).toLowerCase());
                resultado.append(" ");
            }
        }
        
        return resultado.toString().trim();
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
