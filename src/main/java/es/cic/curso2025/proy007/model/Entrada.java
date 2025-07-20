package es.cic.curso2025.proy007.model;

import java.util.Date;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Entradas")
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEntrada;
    
    private int numAsiento;
    private String partido;
    private Date fechaPartido;

    public Long getIdEntrada() {
        return idEntrada;
    }
    public void setIdEntrada(Long idEntrada) {
        this.idEntrada = idEntrada;
    }
    public int getNumAsiento() {
        return numAsiento;
    }
    public void setNumAsiento(int numAsiento) {
        this.numAsiento = numAsiento;
    }
    public String getPartido() {
        return partido;
    }
    public void setPartido(String partido) {
        this.partido = partido;
    }
    public Date getFechaPartido() {
        return fechaPartido;
    }
    public void setFechaPartido(Date fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    
}
