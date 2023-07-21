package br.edu.ufape.clickconsultas.negocios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PlanoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int numero; 
    private String operadora;

    public PlanoSaude(long id, Integer numero, String operadora) {
        this.id = id;
        this.numero = numero;
        this.operadora = operadora;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }
}
