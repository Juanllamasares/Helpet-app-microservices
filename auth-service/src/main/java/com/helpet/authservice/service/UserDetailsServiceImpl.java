package com.helpet.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.helpet.authservice.entity.User;
import com.helpet.authservice.entity.UserPrincipal;
import com.helpet.authservice.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByUsernameOrEmail(username, username);
        if(!user.isPresent()) throw new UsernameNotFoundException("User not exists");
        return UserPrincipal.build(user.get());
    }

   
}
