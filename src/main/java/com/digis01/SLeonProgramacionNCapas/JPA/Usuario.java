/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.JPA;

import com.digis01.SLeonProgramacionNCapas.ML.Direccion;
import com.digis01.SLeonProgramacionNCapas.ML.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author digis
 */
public class Usuario {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idalumno")
     private int IdUsuario;
    @Size(min = 2, max = 20, message = "Texto de entre 2 y 20 letras")
    @NotEmpty(message = "Información necesaria")
    @Pattern(regexp="^[A-Z][a-záéíóú\\s]+$", message="Solo puede contener letras")
    @Column(name = "nombre")
    private String Nombre;
    @Size(min = 2, max = 20, message = "Texto de entre 2 y 20 letras")
    @Pattern(regexp="^[A-Z][a-záéíóú\\s]+$", message="Solo puede contener letras")
    @NotEmpty(message = "Información necesaria")
    @Column(name = "apellidopaterno")
    private String ApellidoPaterno;
    @Past(message = "La fecha de nacimiento debe ser en el pasado.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechanacimiento")
    private Date FechaNacimiento;
    @Size(min = 2, max = 20, message = "Texto de entre 2 y 20 letras")
    @Pattern(regexp="^[A-Z][a-záéíóú\\s]+$", message="Solo puede contener letras")
    @NotEmpty(message = "Información necesaria")
    @Column(name = "apellidomaterno")
    private String ApellidoMaterno;
    @Size(min = 3, max = 20, message = "Username debe ser entre 3 a 20 caracteres")
//    @Pattern(regexp = "^[a-zA-Z0-9_]{8,20}$", 
//             message = "solo puede contener letras, números, puntos, guiones bajos y guiones")
    @NotEmpty(message = "Información necesaria")
    @Column(name = "username")
    private String Username;
    @Pattern(regexp = "^[a-zA-Z0-9]+(?:[._-][a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,}$", message = "Formato de correo electrónico inválido")
    @NotEmpty(message = "Información necesaria")
    @Column(name = "email")
    private String Email;
    @Size(min = 8, max = 20, message = "Tu contrasena debe tener entre 8 and 20 caracteres")
    @NotEmpty(message = "Información necesaria")
    @Column(name = "password")
    private String Password;
    @Pattern(regexp = "^[MF]$", message = "Solo debe ser M o F")
    @NotEmpty(message = "Información necesaria")
    @Column(name = "sexo")
    private String Sexo;
    @Pattern(regexp = "^[0-9]{10}$", message = "Solo debe tener 10 digitos y solo acepta numeros")
    @NotEmpty(message = "Información necesaria")
    @Column(name = "telefono")
    private String Telefono;
    @Pattern(regexp = "^[0-9]{10}$", message = "Solo debe tener 10 digitos y solo acepta numeros")
    @NotEmpty(message = "Información necesaria")
    @Column(name = "celular")
    private String Celular;
    @NotEmpty(message = "Información necesaria")
    @Column(name = "curp")
    private String CURP;
  //  private Integer IdRol;
    @ManyToOne
    @JoinColumn( name = "idrol")
    public Rol Rol;

    @Lob
    @Column( name = "imagen")
    private String Imagen;

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
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

    public Rol getRol() {
        return Rol;
    }

    public void setRol(Rol Rol) {
        this.Rol = Rol;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public Object getDirecciones() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



  
    
    

}