package com.utn.tpreactbackend.utils.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/register").permitAll()  // Permitir acceso sin autenticación a /register
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/instrumentos/**").permitAll()
                                .requestMatchers("/api/pedidoDetalles").permitAll()
                                .requestMatchers("/api/pedidos").permitAll()
                                .anyRequest().authenticated()  // Requerir autenticación para cualquier otra solicitud
                )
                .csrf().disable()  // Desactivar CSRF para simplificar las pruebas
                .formLogin().disable()  // Desactivar el formulario de inicio de sesión por defecto
                .httpBasic().disable();  // Desactivar la autenticación básica

        return http.build();
    }

}
