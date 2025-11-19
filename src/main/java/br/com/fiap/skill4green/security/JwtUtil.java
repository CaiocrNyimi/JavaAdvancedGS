package br.com.fiap.skill4green.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

  private final String secret = "skill4green-secret-key-para-jwt-256bits";
  private final long expiration = 1000 * 60 * 60 * 10;

  private Key getKey() {
    return Keys.hmacShaKeyFor(secret.getBytes());
  }

  public String generateToken(String username) {
    return Jwts.builder()
      .setSubject(username)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + expiration))
      .signWith(getKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder().setSigningKey(getKey()).build()
      .parseClaimsJws(token).getBody().getSubject();
  }

  public boolean isTokenValid(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }
}