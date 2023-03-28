package com.helpet.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpet.authservice.dto.LoginDto;
import com.helpet.authservice.dto.NewUserDto;
import com.helpet.authservice.service.AuthServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthServiceImpl authServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@Valid @RequestBody NewUserDto user){
        authServiceImpl.register(user);
        return new ResponseEntity<>("User created",HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<String> getToken(@Valid @RequestBody LoginDto loginDto){
        return new ResponseEntity<>(authServiceImpl.generateToken(loginDto.getUsername()),HttpStatus.OK) ;
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(String token){
        authServiceImpl.validateToken(token);
        return new ResponseEntity<>("Token is valid",HttpStatus.OK);
    }
}
