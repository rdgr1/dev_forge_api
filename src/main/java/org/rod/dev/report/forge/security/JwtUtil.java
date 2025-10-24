package org.rod.dev.report.forge.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final PrivateKey privateKey;

    public JwtUtil(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String generateToken(String email, String nome) {
        return generateToken(email, nome, Map.of());
    }

    public String generateToken(String email, String nome, Map<String, Object> extraClaims) {
        var builder = Jwts.builder()
                .setSubject(email)
                .claim("nome", nome)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)));

        if (extraClaims != null && !extraClaims.isEmpty()) {
            extraClaims.forEach(builder::claim);
        }

        // 🔑 O ponto crucial: define explicitamente o algoritmo RS256
        return builder.signWith(privateKey, SignatureAlgorithm.RS256).compact();
    }
}