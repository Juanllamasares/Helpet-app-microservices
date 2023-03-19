package com.helpet.authservice.security.service;

import javax.naming.directory.AttributeInUseException;

import com.helpet.authservice.security.dto.CreateAuthUserDto;
import com.helpet.authservice.security.entity.AuthUser;


public interface AuthUserService {
    public AuthUser createUser(CreateAuthUserDto dto) throws AttributeInUseException;
}
