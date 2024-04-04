package com.lina.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "linaiswritingbugslinaiswritingbugslinaiswritingbugs";
    private static Long expire = 43200000L;

    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    public static Claims parseJWT(String jwt){
        // Assuming the secret used to sign the JWT is provided as a String argument
        SecretKey key = Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));

        try {
            Jws<Claims> jwsClaims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);
            return (Claims) jwsClaims;
        } catch (JwtException ex) {
            // Handle exception
            // Examples: ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, etc.
            System.err.println("JWT parsing error: " + ex.getMessage());
            return null; // or handle accordingly
        }
    }
}
