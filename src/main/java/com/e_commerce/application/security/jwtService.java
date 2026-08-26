package com.e_commerce.application.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Service
public class JwtService{

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationTime;

    public String generateToken(String email,String role){
        return Jwts.builder()
        .subject(email)
        .claim("role", role)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expirationTime))
        .signWith(getSigningKey(secretKey))
        .compact();
    }

    private SecretKey getSigningKey(String secretKey){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
            .setSigningKey(getSigningKey(secretKey))
            .build()
            .parseSignedClaims(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public String extractEmail(String token){
        Claims claims = Jwts.parser()
        .setSigningKey(getSigningKey(secretKey))
        .build()
        .parseSignedClaims(token)
        .getBody();
        return claims.getSubject();
    }
    public String extractRole(String token){
        Claims claims = Jwts.parser()
        .setSigningKey(getSigningKey(secretKey))
        .build()
        .parseSignedClaims(token)
        .getBody();
        return claims.get("role", String.class);
    }
}