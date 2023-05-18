package com.helpet.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.helpet.authservice.entity.User;
import com.helpet.authservice.entity.UserDetailsImpl;
import com.helpet.authservice.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsernameOrEmail(username, username);
        if(!user.isPresent()) throw new UsernameNotFoundException("not exists");;
        return UserDetailsImpl.build(user.get());
    }
    
}
