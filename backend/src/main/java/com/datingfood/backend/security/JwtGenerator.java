package com.datingfood.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Component
public class JwtGenerator {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiredate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiredate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return token;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build().
                parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception exception) {
            throw new AuthenticationCredentialsNotFoundException("JWT expired or incorrect");
        }
    }
}
