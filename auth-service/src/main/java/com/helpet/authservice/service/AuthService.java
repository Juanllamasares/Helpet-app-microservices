package com.helpet.authservice.service;

import com.helpet.authservice.dto.NewUserDto;

public interface AuthService {
    public void register(NewUserDto dto);
    public void deleteUser(Long userId);
    public String generateToken(String username);
    public void validateToken(String token);
}
