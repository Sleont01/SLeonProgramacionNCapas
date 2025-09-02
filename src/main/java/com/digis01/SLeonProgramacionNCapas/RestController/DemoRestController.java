/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.RestController;

import com.digis01.SLeonProgramacionNCapas.JPA.Usuario;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoRestController {
    
    @GetMapping
        public Usuario Index(){
            return new Usuario();
        }
        
        @GetMapping("/suma")
        public String Suma(@RequestParam int numeroA, @RequestParam int numeroB){
            return "La suma es " + (numeroA + numeroB);
        }
        
        
        
        @PostMapping("Saludo")
        public String Saludo(@RequestBody Usuario usuario){
            return "Hola " + usuario.getNombre();
        }
        
        
        @PostMapping("/sumarelementos")
        public int sumar(@RequestBody List<Integer> numeros) {
        int suma = 0;
        for (int num : numeros) {
            suma += num;
        }
        return suma;
    }
        
        @PostMapping("/saludos")
        public String saludar(@RequestBody Usuario usuario) {
        return "¡Hola!\n" +
               "Nombre: " + usuario.getNombre() + "\n" +
               "ApellidoPaterno: " + usuario.getApellidoPaterno()+ "\n" +
               "ApellidoMaterno: " + usuario.getApellidoMaterno()+ "\n" +
               "Correo: " + usuario.getEmail();
    }
        
         @PatchMapping("/nombres/{posicion}")
    public List<String> actualizarNombre(
            @RequestBody List<String> nombres,
            @PathVariable int posicion,
            @RequestParam String nuevoNombre) {
        
        if (posicion >= 0 && posicion < nombres.size()) {
            nombres.set(posicion, nuevoNombre);
        }
        return nombres;
    }
        
        
        
        
    
}
