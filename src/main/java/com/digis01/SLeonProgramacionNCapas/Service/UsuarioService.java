
package com.digis01.SLeonProgramacionNCapas.Service;

import com.digis01.SLeonProgramacionNCapas.DAO.IUsuarioRepository;
import com.digis01.SLeonProgramacionNCapas.JPA.Usuario;
import com.digis01.SLeonProgramacionNCapas.ML.Result;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

/**
 *
 * @author digis
 */

@Service
public class UsuarioService {
    
    private final IUsuarioRepository iUsuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(IUsuarioRepository iUsuarioRepository, PasswordEncoder passwordEncoder) {
        this.iUsuarioRepository = iUsuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Result Add(Usuario usuario){
        Result result = new Result();
        
        try{
            
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            
             Usuario savedUsuario = iUsuarioRepository.save(usuario);
            
             result.correct = true;
            result.object = savedUsuario;
        }catch(Exception ex){
            
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        
        }
        return result;
    }
    
    public Result UpdatePasswords(int idUsuario, String nuevaPassword){
    Result result = new Result();

    try {
        
        Usuario usuario = iUsuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        usuario.setPassword(passwordEncoder.encode(nuevaPassword));

        Usuario updatedUsuario = iUsuarioRepository.save(usuario);

        result.correct = true;
        result.object = updatedUsuario;

    } catch (Exception ex) {
        
        result.correct = false;
        result.errorMessage = ex.getLocalizedMessage();
        result.ex = ex;
    }

    return result;
}

    
    
    
}
