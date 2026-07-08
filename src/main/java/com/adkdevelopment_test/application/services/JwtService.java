package com.adkdevelopment_test.application.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET = "energia-solar-chave-secreta-minimo-256-bit-segura";
    private static final long EXPIRACAO_MS = 1000 * 60 * 60 * 24;

    private SecretKey getChave() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String gerarToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRACAO_MS))
                .signWith(getChave())
                .compact();
    }

    public String extrairEmail(String token) {
        return extrairClaims(token).getSubject();
    }

    public boolean isTokenValido(String token, UserDetails userDetails) {
        try {
            String email = extrairEmail(token);
            return email.equals(userDetails.getUsername()) && !isTokenExpirado(token);
        } catch (JwtException | IllegalArgumentException e) {
            // token malformado, assinatura inválida, expirado, etc -> simplesmente inválido
            return false;
        }
    }

    private Claims extrairClaims(String token) {
        return Jwts.parser()
                .verifyWith(getChave())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpirado(String token) {
        return extrairClaims(token).getExpiration().before(new Date());
    }
}