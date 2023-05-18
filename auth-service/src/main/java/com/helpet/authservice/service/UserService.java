package com.helpet.authservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpet.authservice.dto.LoginDto;
import com.helpet.authservice.dto.MessageDto;
import com.helpet.authservice.dto.NewUserDto;
import com.helpet.authservice.dto.TokenDto;
import com.helpet.authservice.dto.UserDto;
import com.helpet.authservice.entity.User;
import com.helpet.authservice.jwt.JwtProvider;
import com.helpet.authservice.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    public MessageDto createUser(NewUserDto dto){
        if(userRepo.existsByUsername(dto.getUsername())) return new MessageDto(HttpStatus.CONFLICT,"username allready exists");
        if(userRepo.existsByEmail(dto.getEmail())) return new MessageDto(HttpStatus.CONFLICT,"email allready exists");
        List<String> roles = Arrays.asList("ROLE_USER");
        dto.setRoles(roles);
        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepo.save(user);
        return new MessageDto(HttpStatus.CREATED,"New user created");
    }

    public MessageDto createAdmin(NewUserDto dto){
        if(userRepo.existsByUsername(dto.getUsername())) return new MessageDto(HttpStatus.CONFLICT,"username allready exists");
        if(userRepo.existsByEmail(dto.getEmail())) return new MessageDto(HttpStatus.CONFLICT,"email allready exists");
        List<String> roles = Arrays.asList("ROLE_ADMIN", "ROLE_USER");
        dto.setRoles(roles);
        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepo.save(user);
        return new MessageDto(HttpStatus.CREATED,"New admin created");
    }

    public List<UserDto> getUsers(){
        List<User> users = userRepo.findAll();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(user -> dtos.add(modelMapper.map(user, UserDto.class)));
        return dtos;
    }

    public UserDto getUserById(Long userId){
        User user = userRepo.findById(userId).get();
        UserDto dto = modelMapper.map(user, UserDto.class);
        return dto;
    }

    public UserDto getUserByUsernameOrEmail(String username,String email){
        User user = userRepo.findByUsernameOrEmail(username, email).get();
        UserDto dto = modelMapper.map(user, UserDto.class);
        return dto;
    }

    public MessageDto deleteUser(Long userId){
        if(userRepo.findById(userId)==null) return new MessageDto(HttpStatus.BAD_REQUEST,"user with id: "+userId+" not exists");
        userRepo.deleteById(userId);
        return new MessageDto(HttpStatus.OK,"user deleted");
    }

    public TokenDto login(LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        return new TokenDto(token);
    }
}
