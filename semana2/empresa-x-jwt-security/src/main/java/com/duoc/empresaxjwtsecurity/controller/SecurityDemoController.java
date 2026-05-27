package com.duoc.empresaxjwtsecurity.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.empresaxjwtsecurity.dto.UserProfileResponse;
import com.duoc.empresaxjwtsecurity.service.AdminReportService;

@RestController
@RequestMapping("/api")
public class SecurityDemoController {

    private final AdminReportService adminReportService;

    public SecurityDemoController(AdminReportService adminReportService) {
        this.adminReportService = adminReportService;
    }

    @GetMapping("/public/info")
    public Map<String, String> publicInfo() {
        return Map.of(
                "message", "Ruta publica disponible sin autenticacion.",
                "securityModel", "stateless con JWT"
        );
    }

    @GetMapping("/usuario/perfil")
    public UserProfileResponse userProfile(Authentication authentication) {
        List<String> roles = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .toList();

        return new UserProfileResponse(authentication.getName(), roles, "JWT stateless");
    }

    @GetMapping("/admin/reporte")
    public Map<String, String> adminReport() {
        return Map.of("report", adminReportService.buildAdminReport());
    }
}
