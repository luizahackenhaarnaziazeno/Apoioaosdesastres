package apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades;

import javax.annotation.processing.Generated;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


@Entity
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private double custoDiario;

   @ManyToOne
   @JoinColumn(name = "equipe_numero")
   private Equipe equipe;

   protected Equipamento() {
    }

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
    
    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
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