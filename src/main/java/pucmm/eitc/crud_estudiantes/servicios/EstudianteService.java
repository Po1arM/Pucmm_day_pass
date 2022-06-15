package pucmm.eitc.crud_estudiantes.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pucmm.eitc.crud_estudiantes.entities.Usuario;
import pucmm.eitc.crud_estudiantes.repo.EstudianteRepository;

import java.util.List;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Transactional
    public Usuario crearEstudiante(Usuario estud){ return estudianteRepository.save(estud);}

    @Transactional
    public Usuario borrarEstudiante(long id){
        Usuario estud = estudianteRepository.findById(id).orElse(null);
        if (estud==null){
            return estud;
        }else{
            estud.setActivo(false);
            estudianteRepository.save(estud);
            return estud;
        }
    }

    public Usuario editarUsuario(Usuario usuario){
        Usuario tmp = estudianteRepository.findById(usuario.getId()).orElse(null);
        if (tmp==null){
            return tmp;
        }else{
            tmp.setNombre(usuario.getNombre());
            tmp.setPassword(usuario.getPassword());
            tmp.setUsername(usuario.getUsername());
            return estudianteRepository.save(tmp);
        }
    }

    public List<Usuario> listar(){
        return estudianteRepository.findAll();
    }
    public Usuario buscar(long id){return estudianteRepository.findById(id).orElse(null);}
}
