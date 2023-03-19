package com.helpet.authservice.security.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.naming.directory.AttributeInUseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpet.authservice.security.dto.CreateAuthUserDto;
import com.helpet.authservice.security.entity.AuthUser;
import com.helpet.authservice.security.enums.RoleEnum;
import com.helpet.authservice.security.repository.AuthUserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthUserServiceImpl implements AuthUserService{

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired

    @Override
    public AuthUser createUser(CreateAuthUserDto dto) throws AttributeInUseException{
        if(authUserRepository.existsByUsername(dto.getUsername())) throw new AttributeInUseException("Username already in use");
        if(authUserRepository.existsByEmail(dto.getEmail()))throw new AttributeInUseException("Email already in use");

        List<RoleEnum> roles = dto.getRoles().stream().map(rol->RoleEnum.valueOf(rol)).collect(Collectors.toList());

        AuthUser entity = new AuthUser(null,dto.getName(),dto.getUsername(),dto.getEmail(),dto.getPassword(),roles);

        return authUserRepository.save(entity);
    }
    
}
