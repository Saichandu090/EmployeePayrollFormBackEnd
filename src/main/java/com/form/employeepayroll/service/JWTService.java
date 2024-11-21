package com.form.employeepayroll.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService
{
    private final String secretKey;

    public JWTService()
    {
        this.secretKey=generateKey();
    }

    public String generateKey()
    {
        try
        {
            KeyGenerator keyGenerator= KeyGenerator.getInstance("HmacSHA256");
            Key key=keyGenerator.generateKey();
            System.out.println("Secret Key : "+key.toString());
            return Base64.getEncoder().encodeToString(key.getEncoded());
        }
        catch(NoSuchAlgorithmException e)
        {
            return e.getMessage();
        }
    }

    public String generateToken(String email) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public boolean validateToken(String token, UserDetails userDetails)
    {
        final String email = extractUserName(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

}