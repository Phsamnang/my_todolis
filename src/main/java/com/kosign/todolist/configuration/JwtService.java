package com.kosign.todolist.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private  static  String SECRET_KEY="3a348509db187c4e8dbda95c5a84791fe9b76bf5d16bf8bb353eb31fe834d454";
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY).build().parseClaimsJws(token)
                .getBody();
    }
  public  <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
  }
  public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
  }

  public boolean isTokenValid(String token,UserDetails userDetails) {
      final String username = extractUsername(token);
      return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
  }
  private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
  }
public String generateToken(Map<String, Objects> extractClaim, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extractClaim)
                .setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+10000*60*24))
                .signWith(getSignIngKey(), SignatureAlgorithm.HS256)
                .compact();
}
    private Key getSignIngKey() {
        byte[] keybite= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keybite);
    }
}
