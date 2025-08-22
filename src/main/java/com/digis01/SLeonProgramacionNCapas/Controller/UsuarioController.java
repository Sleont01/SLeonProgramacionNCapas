package com.digis01.SLeonProgramacionNCapas.Controller;

import com.digis01.SLeonProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.RolDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.DAO.PaisDAOImplementation;
import com.digis01.SLeonProgramacionNCapas.ML.Colonia;
import com.digis01.SLeonProgramacionNCapas.ML.Direccion;
import com.digis01.SLeonProgramacionNCapas.ML.ErrorCM;
import com.digis01.SLeonProgramacionNCapas.ML.Result;
import com.digis01.SLeonProgramacionNCapas.ML.Rol;
import com.digis01.SLeonProgramacionNCapas.ML.Usuario;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;

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
            
             Result result = usuarioDAOImplementation.DireccionesByIdUsuario(IdUsuario);
    
    if (result.correct && result.object != null) {
        Usuario usuario = (Usuario) result.object;

       
        if (usuario.getDirecciones() != null) {
            
            Direccion direccionSeleccionada = null;
            for (Direccion direccion : usuario.getDirecciones()) {
                if (direccion.getIdDireccion() == IdDireccion) {
                    direccionSeleccionada = direccion;
                    break;
                }
            }

            if (direccionSeleccionada != null) {
                List<Direccion> listaDirecciones = new ArrayList<>();
                listaDirecciones.add(direccionSeleccionada);
                usuario.setDirecciones(listaDirecciones);

                model.addAttribute("Usuario", usuario);
                model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            } else {
                model.addAttribute("error", "No se encontró la dirección especificada para este usuario.");
                return "Error";
            }
        } else {
            model.addAttribute("error", "El usuario no tiene direcciones registradas.");
            return "Error";
        }
    } else {
        model.addAttribute("error", "No se pudo cargar la información del usuario.");
        return "Error";
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
            
            if (imagen != null && imagen.getOriginalFilename() != "") {
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
    
     @GetMapping("cargamasiva")
    public String CargaMasiva(){
        return "CargaMasiva";
    }
    
    @PostMapping("cargamasiva")
    public String CargaMasiva(@RequestParam("archivo") MultipartFile file, Model model, HttpSession session){
        
        String root = System.getProperty("user.dir");
        String rutaArchivo = "/src/main/resources/archivos/";
        String fechaSubida = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSS"));
        String rutaFinal = root + rutaArchivo +  fechaSubida + file.getOriginalFilename();
        
        // 7/0
        // if (divisor == 0) throw("error")
        
        try {
            file.transferTo(new File(rutaFinal));
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        } 
        
        if (file.getOriginalFilename().split("\\.")[1].equals("txt")){
            List<Usuario> usuarios = ProcesarTXT(new File(rutaFinal));
            List<ErrorCM> errores = ValidarDatos(usuarios);
            
            if (errores.isEmpty()) {
                model.addAttribute("listaErrores", errores);
                model.addAttribute("archivoCorrecto", true);
                session.setAttribute("path", rutaFinal);
            } else {
                model.addAttribute("listaErrores", errores);
                model.addAttribute("archivoCorrecto", false);
            }
            
            //si lista errores diferente de vacio, intentar desplegar lista de errores en carga masiva
        } else {
             // excel
              List<Usuario> usuarios = ProcesarExcel(new File(rutaFinal));
             List<ErrorCM> errores = ValidarDatos(usuarios);
            
            if (errores.isEmpty()) {
                model.addAttribute("listaErrores", errores);
                model.addAttribute("archivoCorrecto", true);
                session.setAttribute("path", rutaFinal);
            } else {
                model.addAttribute("listaErrores", errores);
                model.addAttribute("archivoCorrecto", false);
            }        
        }
        
        return "CargaMasiva";
    }
    
    @GetMapping("cargamasiva/procesar")
    public String CargaMasiva(HttpSession session){
        try {
            String  ruta = session.getAttribute("path").toString();
            
            List<Usuario> usuarios;
            
            if (ruta.split("\\.")[1].equals("txt")) {
                usuarios = ProcesarTXT(new File(ruta));
            } else {
                usuarios = ProcesarExcel(new File(ruta));
            }
            
            for (Usuario usuario : usuarios) {
                usuarioDAOImplementation.Add(usuario);
            }
            
             session.removeAttribute("path");
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        
        
        return "redirect:/usuario";
        
    }
    
    private List<Usuario> ProcesarTXT(File file){
        
        try {
            
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            
            String linea = ""; 
            List<Usuario> usuarios = new ArrayList<>();
            while ((linea = bufferedReader.readLine()) != null) {                
                String[] campos = linea.split("\\|");
                Usuario usuario = new Usuario();
                usuario.setNombre(campos[0]);
                usuario.setApellidoPaterno(campos[1]);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = formatter.parse(campos[2]);
                usuario.setFechaNacimiento(fechaNacimiento);
                usuario.setApellidoMaterno(campos[3]);
                usuario.setUsername(campos[4]);
                usuario.setEmail(campos[5]);
                usuario.setPassword(campos[6]);
                usuario.setSexo(campos[7]);
                usuario.setTelefono(campos[8]);
                usuario.setCelular(campos[9]);
                usuario.setCURP(campos[10]);
                usuario.Rol = new Rol();
                usuario.Rol.setIdRol(Integer.parseInt(campos[11]));
                
                
                usuario.setDirecciones(new ArrayList<>());
                Direccion direccion = new Direccion();
                direccion.setCalle(campos[12]);
                direccion.setNumeroExterior(campos[13]);
                direccion.setNumeroInterior(campos[14]);

                Colonia colonia = new Colonia();
                colonia.setIdColonia(Integer.parseInt(campos[15])); 
                direccion.setColonia(colonia);

                usuario.getDirecciones().add(direccion);
                usuarios.add(usuario);
                
            }
            return usuarios;
        } catch (Exception ex){
            System.out.println("error");
            return null;
        }
        
    }
    
    private List<Usuario> ProcesarExcel(File file){
        
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
             XSSFWorkbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Usuario usuario = new Usuario();
                usuario.setNombre(row.getCell(0) != null ?  row.getCell(0).toString() : "");
                usuario.setApellidoPaterno(row.getCell(1) != null ?  row.getCell(1).toString() : "");
               // usuario.setFechaNacimiento(row.getCell(2).getDateCellValue());
               usuario.setFechaNacimiento(row.getCell(2) != null && DateUtil.isCellDateFormatted(row.getCell(2)) ? row.getCell(2).getDateCellValue() : null);
                usuario.setApellidoMaterno(row.getCell(3) != null ?  row.getCell(3).toString() : "");
                usuario.setUsername(row.getCell(4) != null ?  row.getCell(4).toString() : "");
                usuario.setEmail(row.getCell(5) != null ?  row.getCell(5).toString() : "");
                usuario.setPassword(row.getCell(6) != null ?  row.getCell(6).toString() : "");
                usuario.setSexo(row.getCell(7) != null ?  row.getCell(7).toString() : "");
                DataFormatter dataFormatter = new DataFormatter();
                usuario.setTelefono(row.getCell(8) != null ? dataFormatter.formatCellValue(row.getCell(8)):"");
                usuario.setCelular(row.getCell(9) != null ? dataFormatter.formatCellValue(row.getCell(9)):"");
                usuario.setCURP(row.getCell(10) != null ? dataFormatter.formatCellValue(row.getCell(10)):"");
                
                usuario.Rol = new Rol();
                usuario.Rol.setIdRol(row.getCell(11) != null ? (int) row.getCell(11).getNumericCellValue() : 0);
                
                usuario.setDirecciones(new ArrayList<>());
                Direccion direccion = new Direccion();
                direccion.setCalle(row.getCell(12) != null ? row.getCell(12).toString() : "");
                direccion.setNumeroExterior(row.getCell(13) != null ? row.getCell(13).toString() : "");
                direccion.setNumeroInterior(row.getCell(14) != null ? row.getCell(14).toString() : "");
                
                direccion.Colonia = new Colonia();
                direccion.Colonia.setIdColonia(row.getCell(15) != null ? (int) row.getCell(15).getNumericCellValue() : 0);
                
                usuario.Direcciones.add(direccion);
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (Exception ex){
            System.out.println("error");
            return null;
        }
    }
    
    private List<ErrorCM> ValidarDatos(List<Usuario> usuarios) {
        List<ErrorCM> errores = new ArrayList<>();

        String soloLetrasPattern = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
        String soloNumerosPattern = "^[0-9]+$";

        int linea = 1;
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre() == null || usuario.getNombre().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getNombre() == null ? "null" : "vacio", "Nombre es un campo obligatorio"));
            } else if (!usuario.getNombre().matches(soloLetrasPattern)) {
                errores.add(new ErrorCM(linea, usuario.getNombre(), "Nombre solo puede contener letras"));
            }
            if (usuario.getApellidoPaterno() == null || usuario.getApellidoPaterno().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getApellidoPaterno() == null ? "null" : "vacio", "Apellido paterno es obligatorio"));
            } else if (!usuario.getApellidoPaterno().matches(soloLetrasPattern)) {
                errores.add(new ErrorCM(linea, usuario.getApellidoPaterno(), "Apellido paterno solo puede contener letras"));
            }

            if (usuario.getFechaNacimiento() == null) {
                errores.add(new ErrorCM(linea, "", "Fecha de nacimiento es obligatoria"));
            }

            if (usuario.getApellidoMaterno() == null || usuario.getApellidoMaterno().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getApellidoMaterno() == null ? "null" : "vacio", "Apellido materno es obligatorio"));
            } else if (!usuario.getApellidoMaterno().matches(soloLetrasPattern)) {
                errores.add(new ErrorCM(linea, usuario.getApellidoMaterno(), "Apellido materno solo puede contener letras"));
            }

            if (usuario.getUsername() == null || usuario.getUsername().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getUsername()== null ? "null" : "vacio", "Username es obligatorio"));
            }

            if (usuario.getEmail() == null || usuario.getEmail().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getEmail()== null ? "null" : "vacio", "Email es obligatorio"));
            } else if (!usuario.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                errores.add(new ErrorCM(linea, usuario.getEmail(), "Formato de email no válido"));
            }

            if (usuario.getPassword() == null || usuario.getPassword().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getPassword()== null ? "null" : "vacio", "Password es obligatorio"));
            }

            if (usuario.getSexo() == null || usuario.getSexo().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getSexo()== null ? "null" : "vacio", "Sexo es obligatorio"));
            }

            if (usuario.getTelefono() == null || usuario.getTelefono().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getTelefono()== null ? "null" : "vacio", "Teléfono es obligatorio"));
            } else if (!usuario.getTelefono().matches(soloNumerosPattern)) {
                errores.add(new ErrorCM(linea, usuario.getTelefono(), "Teléfono solo puede contener números"));
            }

            if (usuario.getCelular() == null || usuario.getCelular().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getCelular()== null ? "null" : "vacio", "Celular es obligatorio"));
            } else if (!usuario.getCelular().matches(soloNumerosPattern)) {
                errores.add(new ErrorCM(linea, usuario.getCelular(), "Celular solo puede contener números"));
            }

            if (usuario.getCURP() == null || usuario.getCURP().trim().equals("")) {
                errores.add(new ErrorCM(linea, usuario.getCURP()== null ? "null" : "vacio", "CURP es obligatorio"));
            }
            if (usuario.Rol.getIdRol() == 0) {
                errores.add(new ErrorCM(linea, usuario.Rol.getIdRol() + "", "Numero de rol no valido"));
            }
            
            if(usuario.Direcciones == null || usuario.Direcciones.isEmpty()){
                errores.add(new ErrorCM(linea, "0", "Debe tener al menos una direccion"));
            } else{
                Direccion direccion = usuario.Direcciones.get(0);
                
                if(direccion.getCalle() == null || direccion.getCalle().trim().equals("")){
                    errores.add(new ErrorCM(linea, direccion.getCalle() == null ? "null" : "vacio", "Calle es obligatoria"));
                }
                if(direccion.getNumeroExterior()== null || direccion.getNumeroExterior().trim().equals("")){
                    errores.add(new ErrorCM(linea, direccion.getNumeroExterior() == null ? "null" : "vacio", "Numero exterior obligatoria"));
                }
                if (direccion.getNumeroInterior() == null || direccion.getNumeroInterior().trim().equals("")) {
                    errores.add(new ErrorCM(linea, direccion.getNumeroInterior() == null ? "null" : "vacio", "Numero interior obligatoria"));
                }
                if (direccion.getNumeroInterior() == null || direccion.getNumeroInterior().trim().equals("")) {
                    errores.add(new ErrorCM(linea, direccion.getNumeroInterior() == null ? "null" : "vacio", "Numero interior obligatoria"));
                }
                if (direccion.Colonia == null) {
                    errores.add(new ErrorCM(linea, "null", "Colonia es obligatoria"));
                } else if (direccion.Colonia.getIdColonia() == 0) {
                    errores.add(new ErrorCM(linea, "0", "ID de colonia no válido"));
                }

            }

            linea++;
        }

        return errores;
    }

}

