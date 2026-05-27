package com.duoc.empresaxjwtsecurity.dto;

public record AuthResponse(String token, String type, long expiresIn) {
}
