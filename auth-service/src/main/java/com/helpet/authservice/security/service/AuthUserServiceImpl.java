package com.helpet.authservice.security.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpet.authservice.security.dto.CreateAuthUserDto;
import com.helpet.authservice.security.dto.JwtTokenDto;
import com.helpet.authservice.security.dto.LoginUserDto;
import com.helpet.authservice.security.entity.AuthUser;
import com.helpet.authservice.security.enums.RoleEnum;
import com.helpet.authservice.security.exception.AttributeException;
import com.helpet.authservice.security.jwt.JwtProvider;
import com.helpet.authservice.security.repository.AuthUserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    public AuthUser create(CreateAuthUserDto dto) throws AttributeException {
        if(authUserRepository.existsByUsername(dto.getUsername()))
            throw new AttributeException("username already in use");
        if(authUserRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("email already in use");
        if(dto.getRoles().isEmpty())
            throw new AttributeException("roles are mandatory");
        return authUserRepository.save(mapUserFromDto(dto));
    }

    public AuthUser createAdmin(CreateAuthUserDto dto) throws AttributeException {
        if(authUserRepository.existsByUsername(dto.getUsername()))
            throw new AttributeException("username already in use");
        if(authUserRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("email already in use");
        List<String> roles = Arrays.asList("ROLE_ADMIN", "ROLE_USER");
        dto.setRoles(roles);
        return authUserRepository.save(mapUserFromDto(dto));
    }

    public AuthUser createUser(CreateAuthUserDto dto) throws AttributeException {
        if(authUserRepository.existsByUsername(dto.getUsername()))
            throw new AttributeException("username already in use");
        if(authUserRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("email already in use");
        List<String> roles = Arrays.asList("ROLE_USER");
        dto.setRoles(roles);
        return authUserRepository.save(mapUserFromDto(dto));
    }

    public JwtTokenDto login(LoginUserDto dto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new JwtTokenDto(token);
    }


    // private methods
    private AuthUser mapUserFromDto(CreateAuthUserDto dto) {
        String password = passwordEncoder.encode(dto.getPassword());
        List<RoleEnum> roles =
                dto.getRoles().stream().map(rol -> RoleEnum.valueOf(rol)).collect(Collectors.toList());
        return new AuthUser(null, dto.getName(),dto.getUsername(), dto.getEmail(), password, roles);
    }
}
