package com.duoc.empresa_x_security.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomControllerSecurity {

    @GetMapping("/app/index_normal")
    public String indexNormal() {
        return "Bienvenido al sitio publico de Empresa X. Este endpoint no requiere autenticacion.";
    }

    @GetMapping("/app/index_protegido")
    public String indexProtegido() {
        return "Bienvenido al area protegida de Empresa X. Para ver este contenido necesitas estar autenticado.";
    }

    @GetMapping("/app/admin")
    public String zonaAdmin() {
        return "Zona administrativa de Empresa X. Este endpoint tambien esta protegido.";
    }

}