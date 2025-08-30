package com.digis01.SLeonProgramacionNCapas.ML;

import com.digis01.SLeonProgramacionNCapas.JPA.Usuario;


public class Direccion {
    
    private int IdDireccion;
    private String Calle;
    private String NumeroInterior;
    private String NumeroExterior;
    public Colonia Colonia;
    
    public Usuario Usuario;
    public int IdUsuario;
    
        public Direccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public Direccion() {
        
    }
    
     public Direccion(com.digis01.SLeonProgramacionNCapas.JPA.Direccion direccionJPA) {
        this.IdDireccion = direccionJPA.getIdDireccion();
        this.Calle = direccionJPA.getCalle();
        this.NumeroInterior = direccionJPA.getNumeroInterior();
        this.NumeroExterior = direccionJPA.getNumeroExterior();
      
        
        this.Colonia = new Colonia();
        this.Colonia.setIdColonia(direccionJPA.Colonia.getIdColonia());
        this.Colonia.setNombre(direccionJPA.Colonia.getNombre());
        this.Colonia.setCodigoPostal(direccionJPA.Colonia.getCodigoPostal());
        
        this.Colonia.Municipio = new Municipio();
        this.Colonia.Municipio.setIdMunicipio(direccionJPA.Colonia.Municipio.getIdMunicipio());
        this.Colonia.Municipio.setNombre(direccionJPA.Colonia.Municipio.getNombre());
        
        this.Colonia.Municipio.Estado = new Estado();
        this.Colonia.Municipio.Estado.setIdEstado(direccionJPA.Colonia.Municipio.Estado.getIdEstado());
        this.Colonia.Municipio.Estado.setNombre(direccionJPA.Colonia.Municipio.Estado.getNombre());
        
        this.Colonia.Municipio.Estado.Pais = new Pais();
        this.Colonia.Municipio.Estado.Pais.setIdPais(direccionJPA.Colonia.Municipio.Estado.Pais.getIdPais());
        this.Colonia.Municipio.Estado.Pais.setNombre(direccionJPA.Colonia.Municipio.Estado.Pais.getNombre());
    }
    

    public Direccion(int IdDireccion, String Calle, String NumeroInterior, String NumeroExterior, int idUsuario) {
        this.IdDireccion = IdDireccion;
        this.Calle = Calle;
        this.NumeroInterior = NumeroInterior;
        this.NumeroExterior = NumeroExterior;
        
         this.IdUsuario = idUsuario;
    }
    
    
    public int getIdDireccion(){
        return IdDireccion;
    }
    
    public void setIdDireccion(int idDireccion){
        this.IdDireccion = idDireccion;
    }
    
    public String getCalle(){
        return Calle;
    }
    
    public void setCalle(String calle){
        this.Calle = calle;
    }
    
    public String getNumeroExterior(){
        return NumeroExterior;
    }
    
    public void setNumeroExterior(String numeroexterior){
        this.NumeroExterior = numeroexterior;
    }
    
    public String getNumeroInterior(){
        return NumeroInterior;
    }
    
    public void setNumeroInterior(String numerointerior){
        this.NumeroInterior = numerointerior;
    }

    public Colonia getColonia() {
        return Colonia;
    }

    public void setColonia(Colonia Colonia) {
        this.Colonia = Colonia;
    }

      public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }
    
}
