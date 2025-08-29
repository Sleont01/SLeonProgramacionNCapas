package com.digis01.SLeonProgramacionNCapas.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Estado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestado")
    private int IdEstado;
    @Column(name = "nombre")
    private String Nombre;
    @ManyToOne
    @JoinColumn(name = "idpais")
    public Pais Pais;

    
    public Estado() {
    }
    
     public Estado(com.digis01.SLeonProgramacionNCapas.ML.Estado estadoML) {
        this.IdEstado = estadoML.getIdEstado();
        this.Nombre = estadoML.getNombre();

       
         this.Pais = new Pais();
         this.Pais.setIdPais(estadoML.Pais.getIdPais());
    }
    
    public Estado(int IdEstado, String Nombre, com.digis01.SLeonProgramacionNCapas.JPA.Pais Pais) {
        this.IdEstado = IdEstado;
        this.Nombre = Nombre;
        this.Pais = Pais;
    }
    
    
    

    public int getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(int IdEstado) {
        this.IdEstado = IdEstado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
}
