package com.ozalp.Velora.Sports.security;

import com.ozalp.Velora.Sports.entities.concretes.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expirationMinute}")
    private long expirationMinute;

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("username", user.getUsername())
                .claim("roles", user.getAllRoles())
                .expiration(new Date(System.currentTimeMillis() * 1000 * 60 * expirationMinute))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }
}
