package com.digis01.SLeonProgramacionNCapas.JPA;

//import com.digis01.SLeonProgramacionNCapas.ML.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.List;

@Entity
public class Direccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddireccion")
    private int IdDireccion;
    @Column(name = "calle")
    private String Calle;
    @Column(name = "numerointerior")
    private String NumeroInterior;
    @Column(name = "numeroexterior")
    private String NumeroExterior;
    @ManyToOne
    @JoinColumn(name = "idcolonia")
    public Colonia Colonia;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario", nullable = false)
    public Usuario Usuario;
    
    
     public Direccion() {
    }
     
     public Direccion(com.digis01.SLeonProgramacionNCapas.ML.Direccion direccionML){
         this.Calle = direccionML.getCalle();
         this.NumeroExterior = direccionML.getNumeroExterior();
         this.NumeroInterior = direccionML.getNumeroInterior();
         
         if(direccionML.getColonia() != null){
             this.Colonia = new Colonia();
             this.Colonia.setIdColonia(direccionML.getColonia().getIdColonia());
            this.Colonia.setNombre(direccionML.getColonia().getNombre());
            this.Colonia.setCodigoPostal(direccionML.getColonia().getCodigoPostal());
            
            Colonia.Municipio = new Municipio();
            Colonia.Municipio.setIdMunicipio(Colonia.Municipio.getIdMunicipio());
            Colonia.Municipio.setNombre(Colonia.Municipio.getNombre());

             Colonia.Municipio.Estado = new Estado();
            Colonia.Municipio.Estado.setIdEstado(Colonia.Municipio.Estado.getIdEstado());
            Colonia.Municipio.Estado.setNombre(Colonia.Municipio.Estado.getNombre());

            Colonia.Municipio.Estado.Pais = new Pais();
            Colonia.Municipio.Estado.Pais.setIdPais(Colonia.Municipio.Estado.Pais.getIdPais());
            Colonia.Municipio.Estado.Pais.setNombre(Colonia.Municipio.Estado.Pais.getNombre());
         }
         
         
     }

    public Direccion(int IdDireccion, String Calle, String NumeroInterior, String NumeroExterior, Colonia Colonia) {
        this.IdDireccion = IdDireccion;
        this.Calle = Calle;
        this.NumeroInterior = NumeroInterior;
        this.NumeroExterior = NumeroExterior;
        this.Colonia = Colonia;
    }
     

    public Direccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

  

    public int getIdDireccion() {
        return IdDireccion;
    }

    public void setIdDireccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public String getNumeroInterior() {
        return NumeroInterior;
    }

    public void setNumeroInterior(String NumeroInterior) {
        this.NumeroInterior = NumeroInterior;
    }

    public String getNumeroExterior() {
        return NumeroExterior;
    }

    public void setNumeroExterior(String NumeroExterior) {
        this.NumeroExterior = NumeroExterior;
    }

    public Colonia getColonia() {
        return Colonia;
    }

    public void setColonia(Colonia Colonia) {
        this.Colonia = Colonia;
    }
    
    
}