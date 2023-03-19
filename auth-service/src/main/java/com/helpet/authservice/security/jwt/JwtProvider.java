package com.helpet.authservice.security.jwt;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import jakarta.annotation.PostConstruct;

@Component
public class JwtProvider {
    
    @Value("${jwt.secret}")
    private String secret;

    @PostConstruct
    protected void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

}
