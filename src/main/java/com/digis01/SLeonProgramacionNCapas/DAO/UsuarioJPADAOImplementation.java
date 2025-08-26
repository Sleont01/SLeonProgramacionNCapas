
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.JPA.Usuario;
import com.digis01.SLeonProgramacionNCapas.ML.Direccion;
import com.digis01.SLeonProgramacionNCapas.ML.Result;
import com.digis01.SLeonProgramacionNCapas.ML.Rol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJPADAOImplementation implements IUsuarioJPADAO{
    
     @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetAll() {
        
        Result result = new Result();
        
        try {
            
            TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario", Usuario.class);
            List<Usuario> usuarios = queryUsuario.getResultList();
            
            /*Bajar a ML para renderizar*/
              List<com.digis01.SLeonProgramacionNCapas.ML.Usuario> usuariosML = new ArrayList<>();
            
            for (Usuario usuarioEntity : usuarios) {
                com.digis01.SLeonProgramacionNCapas.ML.Usuario usuarioML = new com.digis01.SLeonProgramacionNCapas.ML.Usuario();

             usuarioML.setIdUsuario(usuarioEntity.getIdUsuario());
                usuarioML.setNombre(usuarioEntity.getNombre());
                usuarioML.setApellidoPaterno(usuarioEntity.getApellidoPaterno());
                usuarioML.setApellidoMaterno(usuarioEntity.getApellidoMaterno());
                usuarioML.setFechaNacimiento(usuarioEntity.getFechaNacimiento());
                usuarioML.setUsername(usuarioEntity.getUsername());
                usuarioML.setEmail(usuarioEntity.getEmail());
                usuarioML.setPassword(usuarioEntity.getPassword());
                usuarioML.setSexo(usuarioEntity.getSexo());
                usuarioML.setTelefono(usuarioEntity.getTelefono());
                usuarioML.setCelular(usuarioEntity.getCelular());
                usuarioML.setCURP(usuarioEntity.getCURP());
                usuarioML.setIdRol(usuarioEntity.Rol.getIdRol());
                usuarioML.setImagen(usuarioEntity.getImagen());
                
                // Mapear el objeto Rol (si existe en la entidad)
                if (usuarioEntity.getRol() != null) {
                    Rol rolML = new Rol();
                    rolML.setIdRol(usuarioEntity.getRol().getIdRol());
                    rolML.setNombre(usuarioEntity.getRol().getNombre());
                    usuarioML.setRol(rolML);
                }
                
                // Mapear direcciones (si existen)
           /*     if (usuarioEntity.getDirecciones() != null && !usuarioEntity.getDirecciones().isEmpty()) {
                    List<Direccion> direccionesML = new ArrayList<>();
                    for (Direccion direccionEntity : usuarioEntity.getDirecciones()) {
                        Direccion direccionML = new Direccion();
                        direccionML.setIdDireccion(direccionEntity.getIdDireccion());
                        direccionML.setCalle(direccionEntity.getCalle());
                        direccionML.setNumeroExterior(direccionEntity.getNumeroExterior());
                        direccionML.setNumeroInterior(direccionEntity.getNumeroInterior());
                        direccionML.setColonia(direccionEntity.getColonia());
                        // Mapear otras propiedades de Direccion según sea necesario
                        direccionesML.add(direccionML);
                    }
                    usuarioML.setDirecciones(direccionesML);
                }
                
                usuariosML.add(usuarioML);
            }*/
            }
            result.object = usuariosML;
            result.correct = true;
        
        } catch (Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
    
}
