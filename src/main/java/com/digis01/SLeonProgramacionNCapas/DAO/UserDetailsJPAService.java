/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.DAO;

import com.digis01.SLeonProgramacionNCapas.JPA.Usuario;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author digis
 */
@Service
public class UserDetailsJPAService implements UserDetailsService{
    
    private final IUsuarioRepository iUsuarioRepository;

    public UserDetailsJPAService(IUsuarioRepository iUsuarioRepository) {
        this.iUsuarioRepository = iUsuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.digis01.SLeonProgramacionNCapas.JPA.Usuario usuario = iUsuarioRepository.findByUsername(username);
        
//        return User.withUsername(usuario.getUsername())
//                .password(usuario.getPassword())
//                .roles(usuario.Rol.getNombre())
//                .accountLocked(!(usuario.getStatus() == 1))
//                .disabled(!(usuario.getStatus() == 1))
//                .build();
//    
//    }
          if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
          
          if (usuario.getStatus() == 0) {
            throw new DisabledException("Usuario deshabilitado");
        }

        return User.withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRol().getNombre()) 
                .accountLocked(usuario.getStatus() != 1)
                .disabled(usuario.getStatus() != 1)
                .build();
    }
  
    
}
