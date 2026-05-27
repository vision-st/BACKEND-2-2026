package com.duoc.empresaxjwtsecurity.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminReportService {

    @PreAuthorize("hasRole('ADMIN')")
    public String buildAdminReport() {
        return "Reporte administrativo: accesos protegidos correctamente con JWT y rol ADMIN.";
    }
}
