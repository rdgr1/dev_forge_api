package org.rod.dev.report.forge.service;

import lombok.RequiredArgsConstructor;
import org.rod.dev.report.forge.dto.AuthResponse;
import org.rod.dev.report.forge.dto.LoginRequest;
import org.rod.dev.report.forge.repo.UserRepo;
import org.rod.dev.report.forge.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepo repo;

    public AuthResponse login(LoginRequest loginRequest) {
        final String email = loginRequest.email() == null ? "" : loginRequest.email().trim().toLowerCase();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, loginRequest.senha())
            );
            
            var usuario = repo.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));

            String token = jwtUtil.generateToken(email, usuario.getNome());
            return new AuthResponse(token);
            
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Credenciais inválidas!");
        }
    }
}