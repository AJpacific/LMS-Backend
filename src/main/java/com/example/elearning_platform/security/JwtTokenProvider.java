package com.example.elearning_platform.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // Secret key for signing the JWT (must be at least 64 characters for HS256)
    private String secretKey = "MySuperSecretKeyForJWTGenerationMySuperSecretKeyForJWTGeneration";

    // Token validity period in milliseconds (e.g., 1 day)
    private long validityInMilliseconds = 86400000;

    private SecretKey key;

    @PostConstruct
    protected void init() {
        // Generate a secure key based on the secretKey
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // Generate a JWT token with username and role
    public String generateToken(String username, String role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(now)
                .expiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate the token; return true if valid, false otherwise
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // In production, log the exception details
            return false;
        }
    }

    // Extract all claims from the token
    public Claims extractClaims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    // Extract token from the Authorization header in the request
    public String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
