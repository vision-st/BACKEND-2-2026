package com.duoc.empresa_x_security.config;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        // Endpoint publico: cualquier usuario puede acceder
                        .requestMatchers("/app/index_normal").permitAll()

                        // Cualquier otra ruta requiere autenticacion
                        .anyRequest().authenticated()
                )

                // Habilita el formulario de login por defecto de Spring Security
                .formLogin(withDefaults())

                // Habilita autenticacion basica HTTP para probar desde Postman o navegador
                .httpBasic(withDefaults());

        return http.build();
    }

}