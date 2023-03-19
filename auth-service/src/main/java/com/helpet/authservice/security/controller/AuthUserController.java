package com.helpet.authservice.security.controller;

import javax.naming.directory.AttributeInUseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpet.authservice.security.dto.CreateAuthUserDto;
import com.helpet.authservice.security.dto.MessageDto;
import com.helpet.authservice.security.service.AuthUserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private AuthUserServiceImpl authUserServiceImpl;
    
    @PostMapping("/signup")
    public ResponseEntity<MessageDto> signup(@Valid @RequestBody CreateAuthUserDto dto) throws AttributeInUseException{
        authUserServiceImpl.createUser(dto);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "user have been created"));
    }
}
