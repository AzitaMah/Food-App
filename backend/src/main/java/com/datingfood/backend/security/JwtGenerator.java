package com.datingfood.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

@Component
public class JwtGenerator {

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiredate= new Date(currentDate.getTime()+SecurityConstants.JWT_EXPIRATION);

        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiredate)
                .signWith(secretKey)
                .compact();

        return token;
    }
    public String getUsernameFromJWT(String token ){
        //Claims from Token contain Userinformation
        Claims claim = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        // Retrieve and return the username from JWT claims
        return claim.getSubject();
    }

    public boolean validateToken (String token) {
        try{
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJwt(token);
            return true;
        }catch (Exception exception){
            throw new AuthenticationCredentialsNotFoundException("JWT expired or incorrect");
        }
    }
}
