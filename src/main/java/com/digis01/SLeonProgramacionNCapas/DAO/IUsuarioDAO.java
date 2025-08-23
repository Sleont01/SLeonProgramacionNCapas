
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.ML.Result;
import com.digis01.SLeonProgramacionNCapas.ML.Usuario;
public interface IUsuarioDAO {
    
     Result GetAll(Usuario usuario);
     
      Result DireccionesByIdUsuario(int idUsuario);
      
      Result Add(Usuario usuario);
      
      Result Update(Usuario usuario);
      
      Result GetById(int idUsuario);
      
      
      
      
    
}
