package apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "equipe")
public class Equipe {
    @Id
    private Long numero;

    @Column(name = "quantidade")
    private int quantidade;// numero de membros

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    

    public Equipe(long numero, int quantidade, double latitude, double longitude) {
        this.numero = numero; // Assuming no initial equipment
        this.quantidade = quantidade;
        this.latitude = latitude;
        this.longitude = longitude;
        // this.equipamentos = equipamentos;
    }

    

    public Long getNumero() {
        return numero;
    }

    public Long setNumero(Long numero) {
        return this.numero = numero;
    }

   
    public int getQuantidade() {
        return quantidade;
    }

    

    public int setQuantidade(int quantidade) {
        return this.quantidade = quantidade;
    }

    public double getLatitude() {
        return latitude;
    }

    public double setLatitude(double latitude) {
        return this.latitude = latitude;
    }

   
    public double getLongitude() {
        return longitude;
    }

    public double setLongitude(double longitude) {
        return this.longitude = longitude;
    }

    // public List<Equipamento> getEquipamentos() {
    //     // return Collections.unmodifiableList((List<Equipamento>) equipamentos);
    //     return null;
    // }

    public boolean podeAtender(double eventoLatitude, double eventoLongitude) {
        double distancia = calcularDistancia(eventoLatitude, eventoLongitude);
        return distancia <= 5000; // Limite de 5.000 km
    }

    private double calcularDistancia(double eventoLatitude, double eventoLongitude) {
        // Fórmula de Haversine para calcular a distância entre dois pontos geográficos
        double raioTerra = 6371; // Raio médio da Terra em km
        double dLat = Math.toRadians(eventoLatitude - this.latitude);
        double dLon = Math.toRadians(eventoLongitude - this.longitude);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(eventoLatitude))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return raioTerra * c;
    }

    public double calcularCustoEquipe(int duracao) {
        return duracao * 250 * quantidade;
    }

    public double calcularCustoEquipamentos(int duracao) {
        // return duracao * equipamentos.stream()
        //         .mapToDouble(Equipamento::getCustoDiario)
        //         .sum();
        return 0;
    }

    public double calcularCustoDeslocamento(double eventoLatitude, double eventoLongitude) {
        double distancia = calcularDistancia(eventoLatitude, eventoLongitude);

        // Soma de 10% do custo diário de todos os equipamentos
        // double custoEquipamentos = equipamentos.stream()
        //         .mapToDouble(Equipamento::getCustoDiario)
        //         .sum();
        // double custoDeslocamento = distancia * (100 * quantidade + 0.1 * custoEquipamentos);
        // return custoDeslocamento;
        return 0;
    }

    @Override
    public String toString() {
        return "{" +
                " numero=" + numero +
                ", quantidade=" + quantidade +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                // ", equipamentos=" + equipamentos +
                '}';
    }

    public double getDistancia() {
        return calcularDistancia(latitude, longitude);
    }

}