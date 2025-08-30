/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.JPA;

import com.digis01.SLeonProgramacionNCapas.ML.Direccion;
import com.digis01.SLeonProgramacionNCapas.ML.Rol;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author digis
 */
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
     private int IdUsuario;
    
    @Column(name = "nombre")
    private String Nombre;
    
    @Column(name = "apellidopaterno")
    private String ApellidoPaterno;
   
    @Column(name = "fechanacimiento")
    private Date FechaNacimiento;
    
    @Column(name = "apellidomaterno")
    private String ApellidoMaterno;
   
    @Column(name = "username")
    private String Username;
   
    @Column(name = "email")
    private String Email;
    
    @Column(name = "password")
    private String Password;
   
    @Column(name = "sexo")
    private String Sexo;
    
    @Column(name = "telefono")
    private String Telefono;
    
    @Column(name = "celular")
    private String Celular;
    
    @Column(name = "curp")
    private String CURP;
  //  private Integer IdRol;
    
    @ManyToOne
    @JoinColumn( name = "idrol")
    public com.digis01.SLeonProgramacionNCapas.JPA.Rol Rol;

    @Lob
    @Column( name = "imagen")
    private String Imagen;
    
    @OneToMany(mappedBy = "Usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<com.digis01.SLeonProgramacionNCapas.JPA.Direccion> Direcciones = new ArrayList<>();
    
    public Usuario() {
    }
    
    public Usuario (com.digis01.SLeonProgramacionNCapas.ML.Usuario usuarioML){
        this.IdUsuario = usuarioML.getIdUsuario();
        this.Nombre = usuarioML.getNombre();
        this.ApellidoPaterno = usuarioML.getApellidoPaterno();
        this.FechaNacimiento = new java.sql.Date(usuarioML.getFechaNacimiento().getTime());
        this.ApellidoMaterno = usuarioML.getApellidoMaterno();
        this.Username = usuarioML.getUsername();
        this.Email = usuarioML.getEmail();
        this.Password = usuarioML.getPassword();
        this.Sexo = usuarioML.getSexo();
        this.Telefono = usuarioML.getTelefono();
        this.Celular = usuarioML.getCelular();
        this.CURP = usuarioML.getCURP();
        this.Imagen = usuarioML.getImagen();
        this.Imagen = usuarioML.getImagen();
        
        this.Rol = new com.digis01.SLeonProgramacionNCapas.JPA.Rol();
        this.Rol.setIdRol(usuarioML.Rol.getIdRol());
        
        
         if (usuarioML.Direcciones.get(0).getIdDireccion() == -1) {
            usuarioML.Direcciones = null;
        } else {
       
        for (com.digis01.SLeonProgramacionNCapas.ML.Direccion Direccione : usuarioML.Direcciones) { //foreach que usa Modelos ML para iterar
            com.digis01.SLeonProgramacionNCapas.JPA.Direccion direccion = new com.digis01.SLeonProgramacionNCapas.JPA.Direccion(); //Instancia de JPA para vaciar ML en un JPA
            direccion.setCalle(Direccione.getCalle());
            direccion.setNumeroInterior(Direccione.getNumeroInterior());
            direccion.setNumeroExterior(Direccione.getNumeroExterior());
            
            direccion.Colonia = new com.digis01.SLeonProgramacionNCapas.JPA.Colonia();
            direccion.Colonia.setIdColonia(Direccione.Colonia.getIdColonia());
            direccion.Usuario = this;
            
           
            Direcciones.add(direccion); //agregar direcciones vaciadas de ML a JPA
        }
            
        }
    }
      
    

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }


    public Usuario(int IdUsuario, String Nombre, String ApellidoPaterno, Date FechaNacimiento, String ApellidoMaterno, String Username, String Email, String Password, String Sexo, String Telefono, String Celular, String CURP, com.digis01.SLeonProgramacionNCapas.JPA.Rol Rol, String Imagen) {
        this.IdUsuario = IdUsuario;
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.FechaNacimiento = FechaNacimiento;
        this.ApellidoMaterno = ApellidoMaterno;
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
        this.Sexo = Sexo;
        this.Telefono = Telefono;
        this.Celular = Celular;
        this.CURP = CURP;
        this.Rol = Rol;
        this.Imagen = Imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public com.digis01.SLeonProgramacionNCapas.JPA.Rol getRol() {
        return Rol;
    }

    public void setRol(com.digis01.SLeonProgramacionNCapas.JPA.Rol Rol) {
        this.Rol = Rol;
    }

    
    

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }
    
    



  
    
    

}