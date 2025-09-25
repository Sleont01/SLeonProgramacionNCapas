package com.digis01.SLeonProgramacionNCapas.ML;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

public class Usuario {
    
    private int IdUsuario;
    @Size(min = 2, max = 20, message = "Texto de entre 2 y 20 letras")
    @NotEmpty(message = "Información necesaria")
    @Pattern(regexp="^[A-Z][a-záéíóú\\s]+$", message="Solo puede contener letras")
    private String Nombre;
    @Size(min = 2, max = 20, message = "Texto de entre 2 y 20 letras")
    @Pattern(regexp="^[A-Z][a-záéíóú\\s]+$", message="Solo puede contener letras")
    @NotEmpty(message = "Información necesaria")
    private String ApellidoPaterno;
    @Past(message = "La fecha de nacimiento debe ser en el pasado.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date FechaNacimiento;
    @Size(min = 2, max = 20, message = "Texto de entre 2 y 20 letras")
    @Pattern(regexp="^[A-Z][a-záéíóú\\s]+$", message="Solo puede contener letras")
    @NotEmpty(message = "Información necesaria")
    private String ApellidoMaterno;
    @Size(min = 3, max = 20, message = "Username debe ser entre 3 a 20 caracteres")
//    @Pattern(regexp = "^[a-zA-Z0-9_]{8,20}$", 
//             message = "solo puede contener letras, números, puntos, guiones bajos y guiones")
    @NotEmpty(message = "Información necesaria")
    private String Username;
    @Pattern(regexp = "^[a-zA-Z0-9]+(?:[._-][a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,}$", message = "Formato de correo electrónico inválido")
    @NotEmpty(message = "Información necesaria")
    private String Email;
    @Size(min = 8, max = 20, message = "Tu contrasena debe tener entre 8 and 20 caracteres")
    @NotEmpty(message = "Información necesaria")
    private String Password;
    @Pattern(regexp = "^[MF]$", message = "Solo debe ser M o F")
    @NotEmpty(message = "Información necesaria")
    private String Sexo;
    @Pattern(regexp = "^[0-9]{10}$", message = "Solo debe tener 10 digitos y solo acepta numeros")
    @NotEmpty(message = "Información necesaria")
    private String Telefono;
    @Pattern(regexp = "^[0-9]{10}$", message = "Solo debe tener 10 digitos y solo acepta numeros")
    @NotEmpty(message = "Información necesaria")
    private String Celular;
    @NotEmpty(message = "Información necesaria")
    private String CURP;
    private int IdRol;
    public Rol Rol;
    private int Status;

    public List<Direccion> Direcciones;
    private String Imagen;
    
    

    public Usuario() {
    }
    
    public Usuario(com.digis01.SLeonProgramacionNCapas.JPA.Usuario usuarioJPA){
        this.IdUsuario = usuarioJPA.getIdUsuario();
        this.Nombre = usuarioJPA.getNombre();
        this.ApellidoPaterno = usuarioJPA.getApellidoPaterno();
        this.FechaNacimiento = usuarioJPA.getFechaNacimiento();
        this.ApellidoMaterno = usuarioJPA.getApellidoMaterno();
        this.Username = usuarioJPA.getUsername();
        this.Email = usuarioJPA.getEmail();
        this.Password = usuarioJPA.getPassword();
        this.Sexo = usuarioJPA.getSexo();
        this.Telefono = usuarioJPA.getTelefono();
        this.Celular = usuarioJPA.getCelular();
        this.CURP = usuarioJPA.getCURP();
        this.Imagen = usuarioJPA.getImagen();
        this.Status = usuarioJPA.getStatus();
        this.Rol = new Rol();
        this.Rol.setIdRol(usuarioJPA.Rol.getIdRol());
        this.Rol.setNombre(usuarioJPA.Rol.getNombre());
        
        if (usuarioJPA.Direcciones != null && usuarioJPA.Direcciones.size() > 0) {
            this.Direcciones = new ArrayList<>();
            for (com.digis01.SLeonProgramacionNCapas.JPA.Direccion Direccione : usuarioJPA.Direcciones) {
                Direccion direccion = new Direccion();
                direccion.setIdDireccion(Direccione.getIdDireccion());
                direccion.setCalle(Direccione.getCalle());
                direccion.setNumeroExterior(Direccione.getNumeroExterior());
                direccion.setNumeroInterior(Direccione.getNumeroInterior());
                
                direccion.Colonia = new Colonia();
                direccion.Colonia.setIdColonia(Direccione.Colonia.getIdColonia());
                direccion.Colonia.setNombre(Direccione.Colonia.getNombre());
                direccion.Colonia.setCodigoPostal(Direccione.Colonia.getCodigoPostal());
                
                direccion.Colonia.Municipio = new Municipio();
                direccion.Colonia.Municipio.setIdMunicipio(Direccione.Colonia.Municipio.getIdMunicipio());
                direccion.Colonia.Municipio.setNombre(Direccione.Colonia.Municipio.getNombre());
                
                direccion.Colonia.Municipio.Estado = new Estado();
                direccion.Colonia.Municipio.Estado.setIdEstado(Direccione.Colonia.Municipio.Estado.getIdEstado());
                direccion.Colonia.Municipio.Estado.setNombre(Direccione.Colonia.Municipio.Estado.getNombre());
                
                direccion.Colonia.Municipio.Estado.Pais = new Pais();
                direccion.Colonia.Municipio.Estado.Pais.setIdPais(Direccione.Colonia.Municipio.Estado.Pais.getIdPais());
                direccion.Colonia.Municipio.Estado.Pais.setNombre(Direccione.Colonia.Municipio.Estado.Pais.getNombre());
                this.Direcciones.add(direccion);
            }
        }
    }

    public Usuario(int idUsuario, String nombre, String apellidopaterno, Date fechanacimiento, String apellidomaterno,
            String username, String email, String password, String sexo, String telefono, String celular, String curp, int idrol, String imagen, int status) {
        this.IdUsuario = idUsuario;
        this.Nombre = nombre;
        this.ApellidoPaterno = apellidopaterno;

        this.FechaNacimiento = fechanacimiento;

        this.ApellidoMaterno = apellidomaterno;
        this.Username = username;
        this.Email = email;
        this.Password = password;
        this.Sexo = sexo;
        this.Telefono = telefono;
        this.Celular = celular;
        this.CURP = curp;
        this.IdRol = idrol;
        this.Imagen = imagen;
        this.Status = status;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }
    
    
    
    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
    
    public String getNombre(){
        return this.Nombre;
    }
    
    public void setApellidoPaterno(String apellidopaterno) {
        this.ApellidoPaterno = apellidopaterno; 
    }
    
    public String getApellidoPaterno(){
        return ApellidoPaterno;
    }

    
    
    
    public void setFechaNacimiento(Date fechanacimiento){
        this.FechaNacimiento = fechanacimiento;
    }
    
    public Date getFechaNacimiento(){
        return FechaNacimiento;
    }
    
  
    
    public void setApellidoMaterno(String apellidomaterno){
        this.ApellidoMaterno = apellidomaterno;
    }
    
    public String getApellidoMaterno(){
        return ApellidoMaterno;
    }
    
    public void setUsername(String username) {
        this.Username = username;
    }
    
    public String getUsername() {
        return Username;
    }
    
    public void setEmail(String email) {
        this.Email = email;
    }
    
    public String getEmail() {
        return Email;
    }
    
    public void setPassword(String password) {
        this.Password = password;
    }
    
    public String getPassword() {
        return Password;
    }
    
    public void setSexo(String sexo) {
        this.Sexo = sexo;
    }
    
    public String getSexo() {
        return Sexo;
    }
    
    public void setTelefono(String telefono){
        this.Telefono = telefono;
    }
    
    public String getTelefono(){
        return Telefono;
    }
    
    public void setCelular(String celular){
        this.Celular = celular;
    }
    
    public String getCelular(){
        return Celular;
    }
    
    public void setCURP(String curp){
        this.CURP = curp;
    }
    
    public String getCURP(){
        return CURP;
    }
    
    public void setIdRol(int idrol){
        this.IdRol = idrol;
    }
    
    public int getIdRol(){
        return IdRol;
    }

    public Rol getRol() {
        return Rol;
    }

    public void setRol(Rol Rol) {
        this.Rol = Rol;
    }

    

    public List<Direccion> getDirecciones() {
        return Direcciones;
    }

    public void setDirecciones(List<Direccion> Direcciones) {
        this.Direcciones = Direcciones;
    }
    
    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public Usuario(String Nombre, String ApellidoPaterno, String ApellidoMaterno, Rol rol) {
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        this.Rol = rol;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
    
    

    

    

    
    
}
