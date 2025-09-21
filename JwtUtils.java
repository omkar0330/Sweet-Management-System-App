package com.example.sweetshop.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


import java.util.Date;


public class JwtUtils {
    private final String jwtSecret = System.getProperty("jwt.secret", "replace_this_with_a_strong_secret");
    private final long expirationMs = Long.parseLong(System.getProperty("jwt.expirationMs", "3600000"));


    public String generateToken(String username, String role) {
        return JWT.create()
                .withSubject(username)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationMs))
                .sign(Algorithm.HMAC256(jwtSecret));
    }
