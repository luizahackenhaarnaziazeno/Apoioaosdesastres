package apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;



@Entity
public class Atendimento{
    @Id
    private long cod;
    private Date datainicio;
    private int duracao; // dias
    private String status;

    @OneToOne
    @JoinColumn(name = "evento_codigo") 
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "equipe_numero")
    private Equipe equipe;

   
   protected Atendimento() {
    }    

    public Atendimento(long cod, Evento evento, Date datainicio, int duracao, String status) {
        this.cod = cod;
        this.evento = evento;
        this.datainicio = datainicio;
        this.duracao = duracao;
        this.status = status;
    }

    

    public long getCod() {
        return this.cod;
    }

    public Evento getEvento() {
        return this.evento;
    }

    public Date getDatainicio() {
        return this.datainicio;
    }

    public int getDuracao() {
        return this.duracao;
    }

    public String getStatus() {
        return this.status;
    }

    public String setStatus(String status) {
        return this.status = status;
    }

    public Long getEquipes() {
       if (this.equipe == null) {
        return null; 
       }
    return this.equipe.getNumero();
    }

 
     public void setEquipe(Equipe equipe) {
         this.equipe = equipe;
     }

    public Long getEventoCodigo() {
        if (this.evento == null) {
            return null;
        }
        return this.evento.getCodigo();
    }

    public Double calculaCusto(){
        if (this.equipe == null) {
            return null;
        }
         double custoEquipe = this.getDuracao() * 250 * this.equipe.getNumero();
         double custoEquipamentos = this.getDuracao() * equipe.getEquipamentos().stream()
                 .mapToDouble(Equipamento::getCustoDiario)
                .sum();
         double custoDeslocamento = equipe.getDistancia() * 
              (100 * equipe.getNumero() + 
              0.1 * equipe.getEquipamentos().stream()
                .mapToDouble(Equipamento::getCustoDiario)
                .sum());
         double custoTotal = custoEquipe + custoEquipamentos + custoDeslocamento;
         return custoTotal;
    }

}
