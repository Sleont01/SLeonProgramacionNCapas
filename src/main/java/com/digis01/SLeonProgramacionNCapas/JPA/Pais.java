package com.digis01.SLeonProgramacionNCapas.JPA;

import com.digis01.SLeonProgramacionNCapas.ML.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpais")
    private int IdPais;
    @Column(name="nombre")
    private String Nombre;

    
    public Pais() {
    }
    public Pais(int IdPais, String Nombre) {
        this.IdPais = IdPais;
        this.Nombre = Nombre;
    }

    
    
    public int getIdPais() {
        return IdPais;
    }

    public void setIdPais(int idPais) {
        this.IdPais = idPais;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
    
}
