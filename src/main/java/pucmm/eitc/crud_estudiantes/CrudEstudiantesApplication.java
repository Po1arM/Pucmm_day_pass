package pucmm.eitc.crud_estudiantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pucmm.eitc.crud_estudiantes.servicios.SeguridadServices;

@SpringBootApplication
public class CrudEstudiantesApplication {

    public static void main(String[] args) {
        //SpringApplication.run(CrudEstudiantesApplication.class, args);
        ApplicationContext applicationContext = SpringApplication.run(CrudEstudiantesApplication.class, args);
        SeguridadServices seguridadServices = (SeguridadServices) applicationContext.getBean("seguridadServices");
        seguridadServices.crearUsuarioAdmin();
    }

}
