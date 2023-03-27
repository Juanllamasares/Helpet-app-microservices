package com.helpet.authservice.security.service;

import javax.naming.directory.AttributeInUseException;

import com.helpet.authservice.security.dto.CreateAuthUserDto;
import com.helpet.authservice.security.dto.JwtTokenDto;
import com.helpet.authservice.security.dto.LoginUserDto;
import com.helpet.authservice.security.entity.AuthUser;
import com.helpet.authservice.security.exception.AttributeException;


public interface AuthUserService {
    public AuthUser create(CreateAuthUserDto dto) throws AttributeException;
    public AuthUser createUser(CreateAuthUserDto dto) throws AttributeInUseException, AttributeException;
    public AuthUser createAdmin(CreateAuthUserDto dto) throws AttributeException;
    public JwtTokenDto login(LoginUserDto dto);
}
