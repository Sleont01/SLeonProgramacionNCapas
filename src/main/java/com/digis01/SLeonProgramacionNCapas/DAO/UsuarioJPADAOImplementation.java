
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

  
}

