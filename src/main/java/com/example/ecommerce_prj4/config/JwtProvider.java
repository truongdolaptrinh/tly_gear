package com.example.ecommerce_prj4.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtProvider {

    // Ensure SECRET_KEY is a valid 256-bit key (32 characters)
    private final SecretKey key = Keys.hmacShaKeyFor(JWT_CONSTANT.SECRET_KEY.getBytes());

    // Method to generate JWT
    public String generateToken(Authentication auth) {
        String roles = populateAuthorities(auth.getAuthorities());
        return Jwts.builder()
                .setSubject(auth.getName()) // Set email as the subject
                .claim("authorities", roles) // Include roles
                .setIssuedAt(new Date()) // Set issued time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // Expiration (24 hours)
                .signWith(key) // Sign the token
                .compact(); // Generate the token
    }

    // Method to extract email from JWT
    public String getEmailFromJwtToken(String jwt) {
        if (jwt.startsWith("Bearer ")) { // Check for Bearer prefix
            jwt = jwt.substring(7); // Remove the prefix
        }
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key) // Set the signing key
                .build()
                .parseClaimsJws(jwt)
                .getBody(); // Parse the token
        return claims.getSubject(); // Return the subject (email)
    }

    // Helper to format authorities into a comma-separated string
    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
}
