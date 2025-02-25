package com.clm.contactlistmanager.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.function.Function;

/**
 * Utility class for JWT operations.
 * Provides methods to generate, validate and parse JWTs.
 * Uses the io.jsonwebtoken library to handle JWT creation and validation.
 */
@Component  // This tells Spring that this class is a special helper (utility) class
public class JwtUtil {

    // This is our secret password for creating and checking our tokens
    @Value("${jwt.secret}")
    private String secret;

    // This is how long our token will last before it expires
    @Value("${jwt.expiration}")
    private Long expirationTime;

    // Make a new token for a username
    public String generateToken(String username) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
                .setSubject(username)  // This is who the token is for
                .setIssuedAt(new Date(System.currentTimeMillis()))  // This is when we made the token
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))  // This is when the token will expire
                .signWith(key, SignatureAlgorithm.HS512)  // This is us signing the token with our secret password
                .compact();  // This makes our token into a compact string
    }

    // Check if a token is still good and hasn't been tampered with
    public Boolean validateToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(secret.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;  // If we can read it, then it's a good token
        } catch (Exception e) {
            return false;  // If we can't read it, then it's not a good token
        }
    }

    // Get the username out of the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);  // The subject of the token is the username
    }

    // A helper function to get specific info out of the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);  // Get all the info from the token
        return claimsResolver.apply(claims);  // Return the specific piece of info we want
    }

    // Another helper function to get all the info from the token
    private Claims extractAllClaims(String token) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();  // Get everything from the token
    }
}
