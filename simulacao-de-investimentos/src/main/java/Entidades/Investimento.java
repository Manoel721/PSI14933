package Entidades;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Investimento extends PanacheEntity {
    private String nome;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private Float rentabilidade;
    private String risco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setRentabilidade(Float rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public void setRisco(String risco) {
        this.risco = risco;
    }

    public String getTipo() {
        return tipo;
    }

    public Float getRentabilidade() {
        return rentabilidade;
    }

    public String getRisco() {
        return risco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
// Métodos para persistência são herdados de PanacheEntity
}
