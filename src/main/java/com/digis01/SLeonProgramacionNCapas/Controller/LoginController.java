/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author digis
 */

    
    @Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request, @RequestParam(value = "logout", required = false) String logout) {
        Object error = request.getSession().getAttribute("error");
        if (error != null) {
            model.addAttribute("errorMessage", error);
            request.getSession().removeAttribute("error");
        }
        
        if (logout != null) {
        model.addAttribute("logoutMessage", "Has cerrado sesión correctamente");
    }
        return "login"; 
    }


    
}
