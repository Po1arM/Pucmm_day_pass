package pucmm.eitc.crud_estudiantes.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private LocalDate fechaNacimiento;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private EnumRol rol;

    private boolean activo;

    public Usuario() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getEdad() {
        LocalDate now = LocalDate.now();
        return Period.between(this.fechaNacimiento, now).getYears();
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String correo) {
        this.username = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EnumRol getRol() {
        return rol;
    }

    public void setRol(EnumRol rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
