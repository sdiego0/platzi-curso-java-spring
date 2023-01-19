package com.platzi.marker.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JWTUtil {
    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())//Usuario
                .setIssuedAt(new Date())//Fecha de Creacion
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))//Expiracion
                .signWith(SignatureAlgorithm.HS256,key).compact();
    }

    public boolean validateToken(String token,UserDetails userDetails){
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }



    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }
    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before( new Date());
    }
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}