/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.Configuration;

import com.digis01.SLeonProgramacionNCapas.DAO.UserDetailsJPAService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 *
 * @author digis
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    
    private final UserDetailsJPAService userDetailsJPAService;

    public SpringSecurityConfig(UserDetailsJPAService userDetailsJPAService) {
        this.userDetailsJPAService = userDetailsJPAService;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        
        http.authorizeHttpRequests(configurer -> configurer
                
                .requestMatchers("/usuario/cargamasiva/**").hasRole("Administrador")
                .requestMatchers("/usuario/action/**", "/usuario/delete/**").hasRole("Administrador")
                .requestMatchers("/usuario/**")
                .hasAnyRole("Usuario","Cliente","Administrador","Vendedor")
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
        )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/usuario", true)
                        .failureHandler(authenticationFailureHandler())
                        .permitAll()
                        )
        .logout(logout -> logout
                .logoutUrl("/logout") 
                .logoutSuccessUrl("/login?logout") 
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        
                
                
        ).userDetailsService(userDetailsJPAService);
        return http.build();
    }
    
     @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            String errorMessage;

            if (exception instanceof UsernameNotFoundException) {
                errorMessage = "Usuario incorrecto";
            } else if (exception instanceof DisabledException) {
                errorMessage = "Usuario deshabilitado";
            } else if (exception instanceof BadCredentialsException) {
                errorMessage = "Contraseña incorrecta";
            } else {
                errorMessage = "Error de autenticación";
            }

            request.getSession().setAttribute("error", errorMessage);
            response.sendRedirect("/login?error=true");
        };
    }
    
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}test123")
//                .roles("USER")
//                .build();
//        
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}test123")
//                .roles("ADMIN")
//                .build();
//        
//        return new InMemoryUserDetailsManager(user, admin);
//    }
    
    
    }
