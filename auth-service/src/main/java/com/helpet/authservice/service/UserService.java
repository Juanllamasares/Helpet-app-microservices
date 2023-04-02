package com.helpet.authservice.service;

import com.helpet.authservice.dto.LoginDto;
import com.helpet.authservice.dto.MessageDto;
import java.util.List;

import com.helpet.authservice.dto.NewUserDto;
import com.helpet.authservice.dto.TokenDto;
import com.helpet.authservice.dto.UserDto;

public interface UserService {
    public MessageDto createUser(NewUserDto dto);
    public MessageDto createAdmin(NewUserDto dto);
    public TokenDto login(LoginDto loginDto);
    public void deleteUser(Long userId);
    public List<UserDto> getUsers(); 
}
