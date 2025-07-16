package es.cic.curso2025.proy007.model;

import java.util.Date;

public class Entrada {

    private Long idEntrada;
    private int numAsiento;
    private String partido;
    private Date fechaPartido;

    public Entrada() {
        
    }

    public Entrada(Long idEntrada, int numAsiento, String partido, Date fechaPartido) {
        this.idEntrada = idEntrada;
        this.numAsiento = numAsiento;
        this.partido = partido;
        this.fechaPartido = fechaPartido;
    }
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
