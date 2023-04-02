package com.helpet.authservice.controller;

import com.helpet.authservice.dto.LoginDto;
import com.helpet.authservice.dto.MessageDto;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helpet.authservice.dto.NewUserDto;
import com.helpet.authservice.dto.TokenDto;
import com.helpet.authservice.dto.UserDto;
import com.helpet.authservice.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    /* @PostMapping("/register")
    public ResponseEntity<MessageDto> register(@Valid @RequestBody NewUserDto user){
        return new ResponseEntity<>(userServiceImpl.createUser(user),HttpStatus.OK);
    } */

    @PostMapping("/register-admin")
    public ResponseEntity<MessageDto> registerAdmin(@Valid @RequestBody NewUserDto user){
        return new ResponseEntity<>(userServiceImpl.createAdmin(user),HttpStatus.OK);
    }

    @PostMapping("/register-user")
    public ResponseEntity<MessageDto> registerUser(@Valid @RequestBody NewUserDto user){
        return new ResponseEntity<>(userServiceImpl.createUser(user),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto login){
        TokenDto token = userServiceImpl.login(login);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> users = userServiceImpl.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token){
        TokenDto tokenDto = userServiceImpl.validate(token);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }
}
