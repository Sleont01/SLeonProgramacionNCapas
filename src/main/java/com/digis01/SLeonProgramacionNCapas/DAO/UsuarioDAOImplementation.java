/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.ML.Usuario;
import com.digis01.SLeonProgramacionNCapas.ML.Colonia;
import com.digis01.SLeonProgramacionNCapas.ML.Direccion;
import com.digis01.SLeonProgramacionNCapas.ML.Estado;
import com.digis01.SLeonProgramacionNCapas.ML.Municipio;
import com.digis01.SLeonProgramacionNCapas.ML.Pais;
import com.digis01.SLeonProgramacionNCapas.ML.Result;
import com.digis01.SLeonProgramacionNCapas.ML.Rol;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUsuarioDAO{
    
     @Autowired //inyeccion de dependencias 
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {

            // clases Wrapper int - INTEGER, double, float, char
            jdbcTemplate.execute("{CALL UsuarioDireccionGetAll(?)}", (CallableStatementCallback<Integer>) callableStatement -> {
                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);

                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

                result.objects = new ArrayList<>();

            while(resultSet.next()){
                
                int idUsuario = resultSet.getInt("IdUsuario");
                
                if(!result.objects.isEmpty() && idUsuario == ((Usuario)(result.objects.get(result.objects.size() -1 ))).getIdUsuario()){
                    Direccion direccion = new Direccion();
                    direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                    direccion.setCalle(resultSet.getString("Calle"));
                    direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                    direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                    ///////////
                    direccion.Colonia = new Colonia();
                    direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                    direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                    direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                    //////////////////
                    direccion.Colonia.Municipio = new Municipio();
                    direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdColonia"));
                    direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreColonia"));
                    ////////////////////
                    direccion.Colonia.Municipio.Estado = new Estado();
                    direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                    direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));
                    ////////////////////
                    direccion.Colonia.Municipio.Estado.Pais = new Pais();
                    direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                    direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));
                    
                    ((Usuario) (result.objects.get(result.objects.size() -1 ))).Direcciones.add(direccion);
                }else{
                    
                Usuario usuario = new Usuario();
                
                usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                usuario.setNombre(resultSet.getString("NombreUsuario"));
                usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                usuario.setPais(resultSet.getString("Pais"));
                usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                usuario.setEdad(resultSet.getInt("Edad"));
                usuario.setAltura(resultSet.getDouble("Altura"));
                usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                usuario.setUsername(resultSet.getString("Username"));
                usuario.setEmail(resultSet.getString("Email"));
                usuario.setPassword(resultSet.getString("Password")); 
                usuario.setSexo(resultSet.getString("Sexo"));
                usuario.setTelefono(resultSet.getString("Telefono"));
                usuario.setCelular(resultSet.getString("Celular"));
                usuario.setCURP(resultSet.getString("CURP"));
                
                usuario.Rol = new Rol();
                usuario.Rol.setIdRol(resultSet.getInt("IdRol"));
                usuario.Rol.setNombre(resultSet.getString("NombreRol"));
                
                                
                int idDireccion;
                if((idDireccion = resultSet.getInt("IdDireccion")) != 0){
                    
                   usuario.Direcciones = new ArrayList<>();
                   
                    Direccion direccion = new Direccion();
                    direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                    direccion.setCalle(resultSet.getString("Calle"));
                    direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                    direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                    ///////////
                    direccion.Colonia = new Colonia();
                    direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                    direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                    direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                    //////////////////
                    direccion.Colonia.Municipio = new Municipio();
                    direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdColonia"));
                    direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreColonia"));
                    ////////////////////
                    direccion.Colonia.Municipio.Estado = new Estado();
                    direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                    direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));
                    ////////////////////
                    direccion.Colonia.Municipio.Estado.Pais = new Pais();
                    direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                    direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));
                    
                    usuario.Direcciones.add(direccion);
                    
                    
                    
                }
                
                result.objects.add(usuario);
                
                }
            }
                result.correct = true;
                return 1;
            }
            );
            
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
    
    @Override
    public Result DireccionesByIdUsuario(int idUsuario) {
        Result result = new Result();

        try {

            // clases Wrapper int - INTEGER, double, float, char
            jdbcTemplate.execute("{CALL UsuarioDireccionGetByIdUsuario(?,?)}", (CallableStatementCallback<Integer>) callableStatement -> {
                callableStatement.setInt(1, idUsuario);
                callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);

                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

                if (resultSet.next()) {

                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(idUsuario);
                    usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                    usuario.setNombre(resultSet.getString("NombreUsuario"));
                    usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                    usuario.setPais(resultSet.getString("Pais"));
                    usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                    usuario.setEdad(resultSet.getInt("Edad"));
                    usuario.setAltura(resultSet.getDouble("Altura"));
                    usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                    usuario.setUsername(resultSet.getString("Username"));
                    usuario.setEmail(resultSet.getString("Email"));
                    usuario.setPassword(resultSet.getString("Password"));
                    usuario.setSexo(resultSet.getString("Sexo"));
                    usuario.setTelefono(resultSet.getString("Telefono"));
                    usuario.setCelular(resultSet.getString("Celular"));
                    usuario.setCURP(resultSet.getString("CURP"));
                    
                    usuario.Rol = new Rol();
                    usuario.Rol.setIdRol(resultSet.getInt("IdRol"));
                    usuario.Rol.setNombre(resultSet.getString("NombreRol"));
                    
                    
                    
                    int idDireccion;
                    if ((idDireccion = resultSet.getInt("IdDireccion")) != 0) {

                        usuario.Direcciones = new ArrayList<>();

                        do {
                            Direccion direccion = new Direccion();
                            direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                            direccion.setCalle(resultSet.getString("Calle"));
                            direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                            direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                            
                            //resto de datos
                            direccion.Colonia = new Colonia();
                            direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                            direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                            direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                            
                            //////////////////
                            direccion.Colonia.Municipio = new Municipio();
                            direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                            direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));
                            ////////////////////
                            direccion.Colonia.Municipio.Estado = new Estado();
                            direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                            direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));
                            ////////////////////
                            direccion.Colonia.Municipio.Estado.Pais = new Pais();
                            direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                            direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                            usuario.Direcciones.add(direccion);
                        } while (resultSet.next());
                    }
                    result.object = usuario;
                }

                result.correct = true;
                return 1;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
    
    @Override
    public Result Add(Usuario usuario) {
        
        Result result = new Result();
        
        try {
            result.correct = jdbcTemplate.execute("CALL UsuarioDireccionAdd(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", (CallableStatementCallback<Boolean>) callablestatement -> {

                callablestatement.setString(1, usuario.getNombre());
                callablestatement.setString(2, usuario.getApellidoPaterno());
                callablestatement.setString(3, usuario.getPais());
                callablestatement.setDate(4, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
                callablestatement.setInt(5, usuario.getEdad());
                callablestatement.setDouble(6, usuario.getAltura());
                callablestatement.setString(7, usuario.getApellidoMaterno());
                callablestatement.setString(8, usuario.getUsername());
                callablestatement.setString(9, usuario.getEmail());
                callablestatement.setString(10, usuario.getPassword());
                callablestatement.setString(11, usuario.getSexo());
                callablestatement.setString(12, usuario.getTelefono());
                callablestatement.setString(13, usuario.getCelular());
                callablestatement.setString(14, usuario.getCURP());
                callablestatement.setInt(15, usuario.Rol.getIdRol());
                callablestatement.setString(16, usuario.Direcciones.get(0).getCalle());
                callablestatement.setString(17, usuario.Direcciones.get(0).getNumeroExterior());
                callablestatement.setString(18, usuario.Direcciones.get(0).getNumeroInterior());
                callablestatement.setInt(19, usuario.Direcciones.get(0).Colonia.getIdColonia());
                callablestatement.setString(20, usuario.getImagen());

                int isCorrect = callablestatement.executeUpdate();

               if (isCorrect == -1) {

                  return true;
                }
                
                return false;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;

        }
        
         return result;
        
    }
    
    @Override
    public Result Update(Usuario usuario) {
        
        Result result = new Result();
        
        try{
            result.correct = jdbcTemplate.execute("CALL UsuarioDireccionAdd(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", (CallableStatementCallback<Boolean>) callablestatement -> {
                
                callablestatement.setInt(1, usuario.getIdUsuario());
                callablestatement.setString(2, usuario.getNombre());
                callablestatement.setString(3, usuario.getApellidoPaterno());
                callablestatement.setString(4, usuario.getPais());
                callablestatement.setDate(5, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
                callablestatement.setInt(6, usuario.getEdad());
                callablestatement.setDouble(7, usuario.getAltura());
                callablestatement.setString(8, usuario.getApellidoMaterno());
                callablestatement.setString(9, usuario.getUsername());
                callablestatement.setString(10, usuario.getEmail());
                callablestatement.setString(11, usuario.getPassword());
                callablestatement.setString(12, usuario.getSexo());
                callablestatement.setString(13, usuario.getTelefono());
                callablestatement.setString(14, usuario.getCelular());
                callablestatement.setString(15, usuario.getCURP());
                callablestatement.setInt(16, usuario.Rol.getIdRol());
                callablestatement.setString(17, usuario.Direcciones.get(0).getCalle());
                callablestatement.setString(18, usuario.Direcciones.get(0).getNumeroExterior());
                callablestatement.setString(19, usuario.Direcciones.get(0).getNumeroInterior());
                callablestatement.setInt(20, usuario.Direcciones.get(0).Colonia.getIdColonia());
                
                int isCorrect = callablestatement.executeUpdate();
            
                if (isCorrect == -1) {

                  return true;
                }
                
                return false;
            });
        
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
    
         return result;
    
    }
    
    @Override
    public Result GetById(int idUsuario) {
        
        Result result = new Result();
        
         try {

            jdbcTemplate.execute("{CALL UsuarioDireccionGetByIdUsuario(?,?)}", (CallableStatementCallback<Integer>) callableStatement -> {
                callableStatement.setInt(1, idUsuario);
                callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);

                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

                if (resultSet.next()) {

                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(idUsuario);
                    usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                    usuario.setNombre(resultSet.getString("NombreUsuario"));
                    usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                    usuario.setPais(resultSet.getString("Pais"));
                    usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                    usuario.setEdad(resultSet.getInt("Edad"));
                    usuario.setAltura(resultSet.getDouble("Altura"));
                    usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                    usuario.setUsername(resultSet.getString("Username"));
                    usuario.setEmail(resultSet.getString("Email"));
                    usuario.setPassword(resultSet.getString("Password"));
                    usuario.setSexo(resultSet.getString("Sexo"));
                    usuario.setTelefono(resultSet.getString("Telefono"));
                    usuario.setCelular(resultSet.getString("Celular"));
                    usuario.setCURP(resultSet.getString("CURP"));
                    
                    usuario.Rol = new Rol();
                    usuario.Rol.setIdRol(resultSet.getInt("IdRol"));
                    usuario.Rol.setNombre(resultSet.getString("NombreRol"));
                    
                    
                    result.object = usuario;
                }

                result.correct = true;
                return 1;
            });  
            
             } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    
}
