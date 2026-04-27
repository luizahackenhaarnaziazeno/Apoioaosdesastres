package apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades;

@Entity
public class Evento {
    @Id
    private long codigo;
    private String descricao;
    private String data;
    private double latitude;
    private double longitude;

   

    public Evento(long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getdata() {
        return data;
    }

    public void setdata(String data) {
        this.data = data;
    }

    public Double getlatitude() {
        return latitude;
    }

    public void setlatitude(double latitude) {
        this.latitude = latitude;
    }

    public Double getlongitude() {
        return longitude;
    }

    public void setlongitude(double longitude) {
        this.longitude = longitude;
    }

}
