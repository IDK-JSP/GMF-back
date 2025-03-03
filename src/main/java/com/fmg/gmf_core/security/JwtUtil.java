package com.fmg.gmf_core.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExpirationMs;
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));  // Assurez-vous que le secret est suffisamment long
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmailFromToken(String token) {
        try {
            // Analyser et valider le token JWT
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();  // Retourne l'email ou le sujet du token
        } catch (ExpiredJwtException e) {
            // Si le token est expiré, renvoyer une exception spécifique
            throw new IllegalArgumentException("JWT token is expired: " + e.getMessage(), e);
        } catch (UnsupportedJwtException e) {
            // Si le token est du mauvais type
            throw new IllegalArgumentException("JWT token is unsupported: " + e.getMessage(), e);
        } catch (MalformedJwtException e) {
            // Si le token est mal formé
            throw new IllegalArgumentException("Invalid JWT token: " + e.getMessage(), e);
        } catch (SignatureException e) {
            // Si la signature du token est invalide
            throw new IllegalArgumentException("Invalid JWT signature: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            // Si le token est nul ou vide
            throw new IllegalArgumentException("JWT claims string is empty: " + e.getMessage(), e);
        }
    }

    public int getUserIdFromToken(String token) {
        return (int) Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("userId");
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()  // Utilisation de parserBuilder() au lieu de l'ancienne méthode
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            throw new SecurityException("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, "JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("JWT claims string is empty: " + e.getMessage());
        }
    }
}
