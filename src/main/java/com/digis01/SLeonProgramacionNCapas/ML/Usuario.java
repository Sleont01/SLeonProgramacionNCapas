package com.digis01.SLeonProgramacionNCapas.ML;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

public class Usuario {
    
    private int IdUsuario;
    @Size(min = 2, max = 20, message = "Texto de entre 2 y 20 letras")
    @NotEmpty(message = "Información necesaria")
    @Pattern(regexp="^[A-Z][a-zA-ZñÑáéíóúÁÉÍÓÚüÜ\\\\s]+$", message="Solo puede contener letras")
    private String Nombre;
    @Size(min = 2, max = 20, message = "Texto de entre 2 y 20 letras")
    @Pattern(regexp="^[A-Z][a-zA-ZñÑáéíóúÁÉÍÓÚüÜ\\\\s]+$", message="Solo puede contener letras")
    @NotEmpty(message = "Información necesaria")
    private String ApellidoPaterno;
    private String Pais;
    @Past(message = "La fecha de nacimiento debe ser en el pasado.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date FechaNacimiento;
    @Min(value = 18, message = "Tu edad debe ser mayor o igual a 18")
    @Max(value = 100, message = "El número debe ser menor o igual a 100")
    private int Edad;
    
    private double Altura;
    @Size(min = 2, max = 20, message = "Texto de entre 2 y 20 letras")
    @Pattern(regexp="^[A-Z][a-zA-ZñÑáéíóúÁÉÍÓÚüÜ\\\\s]+$", message="Solo puede contener letras")
    @NotEmpty(message = "Información necesaria")
    private String ApellidoMaterno;
    @Size(min = 3, max = 20, message = "Username debe ser entre 3 a 20 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9_]{8,20}$", 
             message = "solo puede contener letras, números, puntos, guiones bajos y guiones")
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

    public List<Direccion> Direcciones;
    private String Imagen;

    public Usuario() {
    }
    
    public Usuario(int idUsuario, String nombre, String apellidopaterno, String pais, Date fechanacimiento, int edad, double altura, String apellidomaterno,
            String username, String email, String password, String sexo, String telefono, String celular, String curp, int idrol){
        this.IdUsuario = idUsuario;
        this.Nombre = nombre;
        this.ApellidoPaterno = apellidopaterno;
        this.Pais = pais;
        this.FechaNacimiento = fechanacimiento;
        this.Edad = edad;
        this.Altura = altura;
        this.ApellidoMaterno = apellidomaterno;
        this.Username = username;
        this.Email = email;
        this.Password = password;
        this.Sexo = sexo;
        this.Telefono = telefono;
        this.Celular = celular;
        this.CURP = curp;
        this.IdRol = idrol;
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

    
    public void setPais(String pais){
        this.Pais = pais;
    }
    
    public String getPais(){
        return Pais;
    }
    
    public void setFechaNacimiento(Date fechanacimiento){
        this.FechaNacimiento = fechanacimiento;
    }
    
    public Date getFechaNacimiento(){
        return FechaNacimiento;
    }
    
    public void setEdad(int edad){
        this.Edad = edad;
    }
    
    public int getEdad(){
        return Edad;
    }
    
    public void setAltura(double altura){
        this.Altura = altura;
    }
    
    public double getAltura(){
        return Altura;
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
    
    
}
