package pucmm.eitc.crud_estudiantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import pucmm.eitc.crud_estudiantes.entities.EnumRol;
import pucmm.eitc.crud_estudiantes.entities.Usuario;
import pucmm.eitc.crud_estudiantes.servicios.EstudianteService;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;

@Controller
public class UsuariosController {
    @Autowired
    private EstudianteService estudianteService;

    @RequestMapping("/")
    public String index(Model model){
        List<Usuario> usuarios = estudianteService.listar();
        usuarios.remove(0);
        model.addAttribute("usuarios",usuarios);
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping("/crearEstuiante")
    public String crearEstudiante(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute("usuario",usuario);
        return "crearUsuario";
    }

    @RequestMapping(path = "/crearUsuario", method = RequestMethod.POST)
    public String registrarUsuario(WebRequest request){
        Usuario usuario = new Usuario();
        usuario.setId(Long.parseLong(request.getParameter("id")));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido(request.getParameter("apellido"));
        usuario.setNacionalidad(request.getParameter("nacionalidad"));
        usuario.setRol(EnumRol.ESTUDIANTE);
        usuario.setActivo(true);
        usuario.setFechaNacimiento(LocalDate.parse(request.getParameter("fechaNacimiento")));
        usuario.setPassword(request.getParameter("pass"));
        usuario.setUsername(request.getParameter("username"));

        estudianteService.crearEstudiante(usuario);

        return "redirect:/";
    }

    @RequestMapping("/borrarUsuario")
    public String borrarUsuario(@PathParam("id") long id){
        estudianteService.borrarEstudiante(id);
        return "redirect:/";
    }

    @RequestMapping("/modificarUsuario")
    public String modificarUsuario(@PathParam("id") long id, Model model){
        Usuario usuario = estudianteService.buscar(id);
        if (usuario != null){
            model.addAttribute("usuario", usuario);
            return "crearUsuario";
        }else{
            return "redirect:/";
        }
    }
}
