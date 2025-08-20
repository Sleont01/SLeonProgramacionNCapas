package com.digis01.SLeonProgramacionNCapas.ML;


public class Direccion {
    
    private int IdDireccion;
    private String Calle;
    private String NumeroInterior;
    private String NumeroExterior;
    public Colonia Colonia;
    
        public Direccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public Direccion() {
        
    }

    public Direccion(int IdDireccion, String Calle, String NumeroInterior, String NumeroExterior) {
        this.IdDireccion = IdDireccion;
        this.Calle = Calle;
        this.NumeroInterior = NumeroInterior;
        this.NumeroExterior = NumeroExterior;
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
    
    
}
