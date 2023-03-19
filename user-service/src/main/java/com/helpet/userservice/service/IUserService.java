package com.helpet.userservice.service;

import java.util.List;

import com.helpet.userservice.dto.RequestUserDto;
import com.helpet.userservice.dto.CreateUserDto;


public interface IUserService {
    
    public RequestUserDto getUserByUsername(String username);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);

    public void createUser(CreateUserDto user);

    public List<RequestUserDto> getUsers();

    public RequestUserDto getUserById(Long id);

    public void deleteUser(Long id);

    public void updateUser(Long id, CreateUserDto userDto);
}
