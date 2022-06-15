package pucmm.eitc.crud_estudiantes.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pucmm.eitc.crud_estudiantes.entities.EnumRol;
import pucmm.eitc.crud_estudiantes.entities.Usuario;
import pucmm.eitc.crud_estudiantes.repo.EstudianteRepository;

import java.util.*;

@Service
public class SeguridadServices implements UserDetailsService {
    @Autowired
    private EstudianteRepository estudianteRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = estudianteRepository.findByUsername(username);

        System.out.println("encontrado" + user.getUsername());

        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        assert user != null;
        roles.add(new SimpleGrantedAuthority(user.getRol().toString()));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isActivo(), true, true, true, grantedAuthorities);

    }

    public void crearUsuarioAdmin() {
        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword(bCryptPasswordEncoder.encode("admin"));
        admin.setNombre("Administrador");
        admin.setActivo(true);
        admin.setRol(EnumRol.ADMIN);
        estudianteRepository.save(admin);
    }
}
