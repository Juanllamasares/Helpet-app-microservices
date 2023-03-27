package com.helpet.authservice.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.helpet.authservice.security.entity.AuthUser;
import com.helpet.authservice.security.repository.AuthUserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> authUser = authUserRepository.findByUsernameOrEmail(username, username);
        if(!authUser.isPresent())
            throw new UsernameNotFoundException("not exists");
        return UserMain.build(authUser.get());
    }

    
}