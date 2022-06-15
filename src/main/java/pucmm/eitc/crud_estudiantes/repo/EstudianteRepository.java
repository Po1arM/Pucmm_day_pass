package pucmm.eitc.crud_estudiantes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pucmm.eitc.crud_estudiantes.entities.Usuario;

public interface EstudianteRepository extends JpaRepository<Usuario,Long> {
    Usuario findByUsername(String username);
}
