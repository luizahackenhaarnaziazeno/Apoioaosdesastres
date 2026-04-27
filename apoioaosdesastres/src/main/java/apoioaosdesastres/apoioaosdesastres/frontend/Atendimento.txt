package apoioaosdesastres.apoioaosdesastres.frontend;

import java.util.Date;


public class Atendimento{
    private long cod;
    private Date datainicio;
    private int duracao; // dias
    private String status;



    protected Atendimento() {
    }

    public Atendimento(long cod, Date datainicio, int duracao, String status) {
        this.cod = cod;
        this.datainicio = datainicio;
        this.duracao = duracao;
        this.status = status;
    }

    public long getCod() {
        return this.cod;
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

    @Override
    public String toString() {
        return "{" +
                " cod='" + getCod() + "'" +
                ", datainicio='" + getDatainicio() + "'" +
                ", duracao='" + getDuracao() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }
}
