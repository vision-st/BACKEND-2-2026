package com.duoc.empresaxjwtsecurity.security;

import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DemoUserDetailsService implements UserDetailsService {

    private static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final Map<String, UserDetails> users;

    public DemoUserDetailsService() {
        this.users = Map.of(
                "usuario_demo", buildUser("usuario_demo", "user123", "USER"),
                "admin_demo", buildUser("admin_demo", "admin123", "ADMIN")
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        return user;
    }

    private UserDetails buildUser(String username, String rawPassword, String role) {
        return User.withUsername(username)
                .password(PASSWORD_ENCODER.encode(rawPassword))
                .roles(role)
                .build();
    }
}
