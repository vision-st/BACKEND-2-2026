package com.duoc.empresaxjwtsecurity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JwtSecurityIntegrationTests {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${local.server.port}")
    private int port;

    @Test
    void debePermitirRutaPublicaSinToken() throws Exception {
        HttpResponse<String> response = sendGet("/api/public/info", null);
        assertEquals(200, response.statusCode());
    }

    @Test
    void debeBloquearRutaProtegidaSinToken() throws Exception {
        HttpResponse<String> response = sendGet("/api/usuario/perfil", null);
        assertEquals(401, response.statusCode());
    }

    @Test
    void debeEntregarTokenAlHacerLogin() throws Exception {
        HttpResponse<String> response = sendLogin("usuario_demo", "user123");
        JsonNode body = objectMapper.readTree(response.body());

        assertEquals(200, response.statusCode());
        assertNotNull(body.get("token").asText());
        assertEquals("Bearer", body.get("type").asText());
    }

    @Test
    void debePermitirPerfilConJwtValido() throws Exception {
        String token = extractToken(sendLogin("usuario_demo", "user123"));
        HttpResponse<String> response = sendGet("/api/usuario/perfil", token);
        JsonNode body = objectMapper.readTree(response.body());

        assertEquals(200, response.statusCode());
        assertEquals("usuario_demo", body.get("username").asText());
    }

    @Test
    void debeBloquearReporteAdminParaUsuarioComun() throws Exception {
        String token = extractToken(sendLogin("usuario_demo", "user123"));
        HttpResponse<String> response = sendGet("/api/admin/reporte", token);
        assertEquals(401, response.statusCode());
    }

    @Test
    void debePermitirReporteAdminConRolCorrecto() throws Exception {
        String token = extractToken(sendLogin("admin_demo", "admin123"));
        HttpResponse<String> response = sendGet("/api/admin/reporte", token);
        assertEquals(200, response.statusCode());
    }

    private HttpResponse<String> sendLogin(String username, String password) throws IOException, InterruptedException {
        String payload = objectMapper.writeValueAsString(new LoginPayload(username, password));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl() + "/api/auth/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private HttpResponse<String> sendGet(String path, String token) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl() + path))
                .GET();
        if (token != null) {
            builder.header("Authorization", "Bearer " + token);
        }
        return httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    private String extractToken(HttpResponse<String> response) throws IOException {
        return objectMapper.readTree(response.body()).get("token").asText();
    }

    private String baseUrl() {
        return "http://localhost:" + port;
    }

    private record LoginPayload(String username, String password) {
    }
}
