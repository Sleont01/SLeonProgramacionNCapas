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
public class Colonia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcolonia")
    private int IdColonia;
    @Column(name="nombre")
    private String Nombre;
    @Column(name="codigopostal")
    private String CodigoPostal;
    @ManyToOne
    @JoinColumn(name = "idmunicipio")
    public Municipio Municipio;
    
     public Colonia(int IdColonia) {
        this.IdColonia = IdColonia;
    }
    
    public Colonia() {
    }

    public Colonia(int IdColonia, String Nombre, String CodigoPostal, com.digis01.SLeonProgramacionNCapas.JPA.Municipio Municipio) {
        this.IdColonia = IdColonia;
        this.Nombre = Nombre;
        this.CodigoPostal = CodigoPostal;
        this.Municipio = Municipio;
    }

    public int getIdColonia() {
        return IdColonia;
    }

    public void setIdColonia(int IdColonia) {
        this.IdColonia = IdColonia;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }
}
