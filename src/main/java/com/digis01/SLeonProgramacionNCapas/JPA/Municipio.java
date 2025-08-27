package com.digis01.SLeonProgramacionNCapas.JPA;

import com.digis01.SLeonProgramacionNCapas.ML.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Municipio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmunicipio")
    private int IdMunicipio;
    @Column(name = "nombre")
    private String Nombre;
    @ManyToOne
    @JoinColumn(name="idestado")
    public Estado Estado;

    
    public Municipio() {
    }
    public Municipio(int IdMunicipio, String Nombre, com.digis01.SLeonProgramacionNCapas.JPA.Estado Estado) {
        this.IdMunicipio = IdMunicipio;
        this.Nombre = Nombre;
        this.Estado = Estado;
    }
    
    

    public int getIdMunicipio() {
        return IdMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.IdMunicipio = idMunicipio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
    
    
}
