package apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Equipamento {

    @Id
    private long id;

    private String nome;
    private double custoDiario;

    public Equipamento(long id, String nome, double custoDiario) {
        this.id = id;
        this.nome = nome;
        this.custoDiario = custoDiario;
    }

    public long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public double getCustoDiario() {
        return this.custoDiario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCustoDiario(double custoDiario) {
        this.custoDiario = custoDiario;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", nome='" + getNome() + "'" +
                ", custoDiario='" + getCustoDiario() + "'" +
                "}";
    }
}