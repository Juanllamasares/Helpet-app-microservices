package com.helpet.authservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpet.authservice.dto.NewUserDto;
import com.helpet.authservice.entity.User;
import com.helpet.authservice.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public void register(NewUserDto dto) {
        User entity = modelMapper.map(dto, User.class);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepo.save(entity);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public String generateToken(String username){
        return jwtService.generateToken(username);
    }

    @Override
    public void validateToken(String token){
        jwtService.validateToken(token);
    }
    
}
