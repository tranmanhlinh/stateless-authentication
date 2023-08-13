package com.linhtran.statelessauthentication.service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JWTService {
    private final String secret = "Jwt secret";

    public String generateJwt(String username, String role) {
        Key key = new SecretKeySpec(secret.getBytes(),
                SignatureAlgorithm.HS256.getJcaName());
        Instant now = Instant.now();
        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public String validate(String token) {
        try {
            Key key = new SecretKeySpec(secret.getBytes(),
                    SignatureAlgorithm.HS256.getJcaName());
            Claims claims = Jwts.parser()
                    .setSigningKey(key.getEncoded())
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("role", String.class);
        } catch (Exception e) {
            return null;
        }
    }
}
