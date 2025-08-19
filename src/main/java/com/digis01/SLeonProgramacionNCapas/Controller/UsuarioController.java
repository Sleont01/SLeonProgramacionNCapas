package com.digis01.SLeonProgramacionNCapas.Controller;

import com.digis01.SLeonProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.RolDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.PaisDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.ML.Direccion;
import com.digis01.SLeonProgramacionNCapas.ML.Result;
import com.digis01.SLeonProgramacionNCapas.ML.Usuario;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Base64;
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
import org.springframework.web.multipart.MultipartFile;

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
           
            Result result = usuarioDAOImplementation.GetById(IdUsuario);
//            Usuario usuario = new Usuario();
//            
//            usuario = (Usuario) result.object;
            
       if(result.correct && result.object != null){
        Usuario usuario = (Usuario) result.object;
        
       
        if (usuario.getDirecciones() == null) {
            usuario.setDirecciones(new ArrayList<>());
        }
        
        
        usuario.getDirecciones().add(new Direccion(-1));
        
        model.addAttribute("Usuario", usuario); 
        model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
    } else {
        model.addAttribute("error", "No se pudo cargar la información del usuario");
        return "Error";
    }
            
           
   
        } 
        
        else if (IdDireccion == 0) {//Agregar direccion
             Usuario usuario = new Usuario();
        usuario.setIdUsuario(IdUsuario);
        usuario.setDirecciones(new ArrayList<>());
        usuario.getDirecciones().add(new Direccion(0)); 
        
        model.addAttribute("Usuario", usuario);
        
        model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            
        } else { // editar direccion
            /*recuperar información de la direccion*/
            Result resultDireccion = usuarioDAOImplementation.GetById(IdDireccion);
            if (resultDireccion.correct && resultDireccion.object != null) {
                Direccion direccion = (Direccion) resultDireccion.object;
                Usuario usuario = new Usuario(); 
                usuario.setIdUsuario(IdUsuario);
                usuario.setDirecciones(new ArrayList<>());
                usuario.getDirecciones().add(direccion);
                model.addAttribute("Usuario", usuario);
            }
        }

        return "UsuarioForm";
    }
    
    @PostMapping("add") 
    public String Add(@Valid @ModelAttribute("Usuario") Usuario usuario, BindingResult bindingResult,
            Model model,  @RequestParam("imagenFile") MultipartFile imagen){
        
        if(bindingResult.hasErrors()){
            model.addAttribute("Usuario", usuario);
            return "UsuarioForm";
        }else{
            
            if (imagen != null) {
                String nombre = imagen.getOriginalFilename();
                //archivo.jpg
                //[archivo,jpg]
                String extension = nombre.split("\\.")[1];
                if (extension.equals("jpg")) {
                    try {
                        byte[] bytes = imagen.getBytes();
                        String base64Image = Base64.getEncoder().encodeToString(bytes);
                        usuario.setImagen(base64Image);
                    } catch (Exception ex) {
                        System.out.println("Error");
                    }

                }
            }
                Result result = usuarioDAOImplementation.Add(usuario);
                return "redirect:/usuario";
    
//    Result result = usuarioDAOImplementation.Add(usuario);
//        return "redirect:/usuario";
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

