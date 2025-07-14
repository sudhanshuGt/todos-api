package com.app.todo.todos.util;

 
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import jakarta.annotation.PostConstruct;


 
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private SecretKey secretKey;

    private final long EXPIRATION = 1000 * 60 * 60; 
    private static final String SECRET = "KJKFKW34KJKJKKWEJKWEJR3003223MNWKENFKWENFFZMXXK";


    @PostConstruct
    public void init() {
          secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generate(Long userId, String roles) {
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("roles", roles)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(secretKey)
                .compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)  
                .build()
                .parseSignedClaims(token);
    }
}


