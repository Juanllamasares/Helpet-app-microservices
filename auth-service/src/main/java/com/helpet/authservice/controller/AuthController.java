package com.helpet.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpet.authservice.dto.LoginDto;
import com.helpet.authservice.dto.MessageDto;
import com.helpet.authservice.dto.NewUserDto;
import com.helpet.authservice.dto.TokenDto;
import com.helpet.authservice.dto.UserDto;
import com.helpet.authservice.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<MessageDto> registerUser(@Valid @RequestBody NewUserDto dto){
        MessageDto response = userService.createUser(dto);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @PostMapping("/register-admin")
    public ResponseEntity<MessageDto> registerAdmin(@Valid @RequestBody NewUserDto dto){
        MessageDto response = userService.createAdmin(dto);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto){
        TokenDto tokenDto = userService.login(loginDto);
        return ResponseEntity.ok(tokenDto);
    }
}
