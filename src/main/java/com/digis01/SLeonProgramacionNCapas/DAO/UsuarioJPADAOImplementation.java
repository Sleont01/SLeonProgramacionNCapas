
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.JPA.Usuario;
import com.digis01.SLeonProgramacionNCapas.ML.Direccion;
import com.digis01.SLeonProgramacionNCapas.ML.Result;
import com.digis01.SLeonProgramacionNCapas.ML.Rol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
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
            
          //  TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario", Usuario.class);
           TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario ORDER BY IdUsuario", Usuario.class);
            List<Usuario> usuarios = queryUsuario.getResultList();

            result.objects = new ArrayList<>();
            
            for (Usuario usuario : usuarios) {
                result.objects.add(new com.digis01.SLeonProgramacionNCapas.ML.Usuario(usuario));
            }
            
            result.correct = true;
            
            

        } catch (Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Transactional
    @Override
    public Result ADD(com.digis01.SLeonProgramacionNCapas.ML.Usuario usuarioML) {
        Result result = new Result();

        try {

            Usuario usuarioJPA = new Usuario(usuarioML);

            entityManager.persist(usuarioJPA);
            
            result.correct = true; 

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }

    @Transactional
    @Override
    public Result Delete(int IdUsuario) {
        Result result = new Result();
        
        try {
            
            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);
            entityManager.remove(usuarioJPA);
            
            result.correct = true; 
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex; 
        }
        
        return result;
    }
  
    @Transactional
    @Override
    public Result Update(com.digis01.SLeonProgramacionNCapas.ML.Usuario usuarioML) {
        Result result = new Result();

        try {
           
            Usuario usuarioJPA = new Usuario(usuarioML);
            Usuario usuarioBD = entityManager.find(Usuario.class, usuarioML.getIdUsuario());
            
            usuarioJPA.Direcciones = usuarioBD.Direcciones;
            
            entityManager.merge(usuarioJPA);
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result GetById(int IdUsuario) {
        Result result = new Result();
        
        try{
            
            Usuario usuarioJPA =  entityManager.find(Usuario.class, IdUsuario);
            com.digis01.SLeonProgramacionNCapas.ML.Usuario usuarioML = new com.digis01.SLeonProgramacionNCapas.ML.Usuario(usuarioJPA);
            result.object = usuarioML;
            result.correct = true;
            
        } catch (Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }

  
}

