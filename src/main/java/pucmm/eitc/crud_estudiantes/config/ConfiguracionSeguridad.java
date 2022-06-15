package pucmm.eitc.crud_estudiantes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {
    //Opción JPA
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * La autentificación de los usuarios.
     * param auth
     * throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Clase para encriptar contraseña
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        //Configuración JPA.
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    /*
     * Permite configurar las reglas de seguridad.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Marcando las reglas para permitir unicamente los usuarios
        http
                .authorizeRequests()
                .antMatchers("/","/css/**", "/js/**", "/actuator/**", "/webjars/**").permitAll() // cosas base
                .antMatchers("/admin").authenticated()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") //indicando la ruta que estaremos utilizando.
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/admin", true)
                .failureUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .permitAll();

        //TODO: validar exclusivamente en ambiente de prueba.
        // deshabilitando las seguridad contra los frame internos.
        //if(!profiles.matches(Pre"prod")){
        //Necesario para H2.
        http.csrf().disable();
        http.headers().frameOptions().disable();
        //}
    }
}
