package com.duoc.empresaxjwtsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.duoc.empresaxjwtsecurity.dto.AuthRequest;
import com.duoc.empresaxjwtsecurity.dto.AuthResponse;
import com.duoc.empresaxjwtsecurity.security.DemoUserDetailsService;
import com.duoc.empresaxjwtsecurity.security.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final DemoUserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(DemoUserDetailsService userDetailsService,
                          JwtService jwtService,
                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@RequestBody AuthRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
        if (!passwordEncoder.matches(request.password(), userDetails.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales invalidas");
        }
        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token, "Bearer", jwtService.getExpirationSeconds());
    }
}
