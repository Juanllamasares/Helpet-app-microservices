package com.helpet.userservice.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpet.userservice.dto.RequestUserDto;
import com.helpet.userservice.dto.CreateUserDto;
import com.helpet.userservice.entity.User;
import com.helpet.userservice.repository.IUserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RequestUserDto getUserByUsername(String username) {
        if(userRepo.findByUsername(username) == null) return null;

        User userEntity = userRepo.findByUsername(username).get();

        RequestUserDto userDto = modelMapper.map(userEntity, RequestUserDto.class);

        return userDto;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public void createUser(CreateUserDto user) {
        User userEntity = modelMapper.map(user, User.class);
        userRepo.save(userEntity);
    }

    @Override
    public List<RequestUserDto> getUsers() {
        List<User> usersEntities = userRepo.findAll();
        List<RequestUserDto> usersDtos = new ArrayList<>();

        for (User entity : usersEntities){
            usersDtos.add(modelMapper.map(entity, RequestUserDto.class));
        }

        return usersDtos;
    }

    @Override
    public RequestUserDto getUserById(Long id) {
        User userEntity = userRepo.findById(id).orElse(null);
        if(userEntity == null) return null;
        return modelMapper.map(userEntity, RequestUserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
    

}
