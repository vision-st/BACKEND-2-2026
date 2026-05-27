package com.duoc.empresa_x_security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecurityIntegrationTests {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Value("${local.server.port}")
    private int port;

    @Test
    void debePermitirAccesoPublicoSinAutenticacion() throws IOException, InterruptedException {
        HttpResponse<String> response = sendGet("/app/publico", null);

        assertEquals(200, response.statusCode());
        assertEquals("Bienvenido al portal publico de Empresa X.", response.body());
    }

    @Test
    void debeBloquearPerfilSinAutenticacion() throws IOException, InterruptedException {
        HttpResponse<String> response = sendGet("/app/usuario/perfil", null);

        assertEquals(401, response.statusCode());
    }

    @Test
    void debePermitirPerfilConUsuarioValido() throws IOException, InterruptedException {
        HttpResponse<String> response = sendGet("/app/usuario/perfil", basicAuth("usuario_demo", "user123"));

        assertEquals(200, response.statusCode());
        assertEquals("Perfil de usuario autenticado en Empresa X.", response.body());
    }

    @Test
    void debeRestringirPanelAdminParaUsuarioSinRolAdmin() throws IOException, InterruptedException {
        HttpResponse<String> response = sendGet("/app/admin/panel", basicAuth("usuario_demo", "user123"));

        assertEquals(403, response.statusCode());
    }

    @Test
    void debePermitirPanelAdminConRolAdmin() throws IOException, InterruptedException {
        HttpResponse<String> response = sendGet("/app/admin/panel", basicAuth("admin_demo", "admin123"));

        assertEquals(200, response.statusCode());
        assertEquals("Panel administrativo de Empresa X.", response.body());
    }

    private HttpResponse<String> sendGet(String path, String authHeader) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + path))
                .GET();

        if (authHeader != null) {
            builder.header("Authorization", authHeader);
        }

        return httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    private String basicAuth(String username, String password) {
        String credentials = username + ":" + password;
        String encoded = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        return "Basic " + encoded;
    }
}
