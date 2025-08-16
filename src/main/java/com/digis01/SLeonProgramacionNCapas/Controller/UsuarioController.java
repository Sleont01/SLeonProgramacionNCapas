package com.digis01.SLeonProgramacionNCapas.Controller;

import com.digis01.SLeonProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.RolDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.PaisDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.ML.Result;
import com.digis01.SLeonProgramacionNCapas.ML.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    
    @Autowired
    private PaisDAOImplementation paisDAOImplementation;
    
    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;
    
    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;
    
    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;
    
    @Autowired
    private RolDAOImplementation rolDAOImplementation;
    
    
    
    
    @GetMapping
    public String Index(Model model){
        Result result = usuarioDAOImplementation.GetAll();
        
        if (result.correct) {
            model.addAttribute("usuarios", result.objects);
        } else  {
            model.addAttribute("usuarios", null);
        }
        
        return "UsuarioIndex";
    }
    
  /*  @GetMapping("usuarioDetail/{idUsuario}")
    public String UsuarioDetail(@PathVariable int idUsuario, Model model){
        
        Result result = usuarioDAOImplementation.DireccionesByIdUsuario(idUsuario);
        
        if (result.correct) {
            model.addAttribute("usuario", result.object);
        } else {
            return "Error";
        }
        
        return "UsuarioDetail";
    }
    
    @GetMapping("add")
    public String add(Model model){
        
        model.addAttribute("roles",rolDAOImplementation.GetAll().objects);
        model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
        model.addAttribute("Usuario", new Usuario());
        
        return "UsuarioForm";
    }
    */
    
    @GetMapping("/action/{IdUsuario}")
    public String add(Model model, @PathVariable("IdUsuario") int IdUsuario){
        
        if(IdUsuario == 0){
            model.addAttribute("roles",rolDAOImplementation.GetAll().objects);
            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            model.addAttribute("Usuario", new Usuario());
            
            return "UsuarioForm";
        }else{
            Result result = usuarioDAOImplementation.DireccionesByIdUsuario(IdUsuario);
            
            if(result.correct){
                model.addAttribute("usuario", result.object);
            }else{
                return "Error";
            }
            
            return "UsuarioDetail";
        }
        
    
    }
    //usuario/formEditable?IdUsuario=1&IdDireccion=2
    @GetMapping("/formEditable")
    public String formEditable(
                @RequestParam int IdUsuario, 
                @RequestParam(required = false) Integer IdDireccion,
                Model model){
                
            
        if (IdDireccion == null) {  // Editar Usuario
            /* recuperar información del usuario*/
            //UsuarioGetById
            
           Result result = usuarioDAOImplementation.GetById(IdUsuario);
           
           if(result.correct){
               model.addAttribute("Usuario", result.objects);
           }
   
        } else if (IdDireccion == 0) {//Agregar direccion
            
            
        } else { // editar direccion
            /*recuperar información de la direccion*/
           
        }
        
        return "UsuarioForm";
    }
    
    @PostMapping("add") 
    public String Add(@Valid @ModelAttribute("Usuario") Usuario usuario, BindingResult bindingResult,
            Model model){
        
        if(bindingResult.hasErrors()){
            model.addAttribute("Usuario", usuario);
            return "UsuarioForm";
        }else{
    
    Result result = usuarioDAOImplementation.Add(usuario);
        return "redirect:/usuario";
        }   
    }
    
    
    @GetMapping("getEstadosByPais/{IdPais}")
    @ResponseBody
    public Result EstadoByPais(@PathVariable int IdPais){
    
        return estadoDAOImplementation.EstadoByPais(IdPais);
    }
    
    @GetMapping("getMunicipiosByEstado/{IdEstado}")
    @ResponseBody
    public Result MunicipioByEstado(@PathVariable int IdEstado){
    
        return municipioDAOImplementation.MunicipioByEstado(IdEstado);
    }
    
    @GetMapping("getColoniasByMunicipio/{IdMunicipio}")
    @ResponseBody
    public Result ColoniaByMunicipio(@PathVariable int IdMunicipio){
    
        return coloniaDAOImplementation.ColoniaByMunicipio(IdMunicipio);
    }
}

