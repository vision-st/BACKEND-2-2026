package com.duoc.empresa_x_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityDemoController {

    @GetMapping("/app/publico")
    public String publico() {
        return "Bienvenido al portal publico de Empresa X.";
    }

    @GetMapping("/app/usuario/perfil")
    public String perfilUsuario() {
        return "Perfil de usuario autenticado en Empresa X.";
    }

    @GetMapping("/app/admin/panel")
    public String panelAdmin() {
        return "Panel administrativo de Empresa X.";
    }
}
