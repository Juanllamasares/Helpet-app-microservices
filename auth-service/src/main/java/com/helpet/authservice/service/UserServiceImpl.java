package com.helpet.authservice.service;

import com.helpet.authservice.dto.LoginDto;
import com.helpet.authservice.dto.MessageDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpet.authservice.dto.NewUserDto;
import com.helpet.authservice.dto.TokenDto;
import com.helpet.authservice.dto.UserDto;
import com.helpet.authservice.entity.User;
import com.helpet.authservice.enums.RolEnums;
import com.helpet.authservice.jwt.JwtProvider;
import com.helpet.authservice.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider JwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public MessageDto createUser(NewUserDto dto) {

        if (userRepo.existsByUsername(dto.getUsername())) {
            return new MessageDto(HttpStatus.BAD_REQUEST, "Username allready exists");
        }
        if (userRepo.existsByEmail(dto.getEmail())) {
            return new MessageDto(HttpStatus.BAD_REQUEST, "Email allready exists");
        }

        String password = passwordEncoder.encode(dto.getPassword());
        List<String> rolesDefault = Arrays.asList("ROLE_USER");
        dto.setRoles(rolesDefault);

        try {
            List<RolEnums> roles = dto.getRoles().stream().map(rol -> RolEnums.valueOf(rol)).collect(Collectors.toList());

            User user = new User(null, dto.getName(), dto.getUsername(), dto.getEmail(), password, dto.getAvatar(), roles);

            userRepo.save(user);
        } catch (Exception e) {
            return new MessageDto(HttpStatus.BAD_REQUEST,"Invalid roles");
        }

        return new MessageDto(HttpStatus.CREATED, "User successfully added");
    }

    public MessageDto createAdmin(NewUserDto dto) {

        if (userRepo.existsByUsername(dto.getUsername())) {
            return new MessageDto(HttpStatus.BAD_REQUEST, "Username allready exists");
        }
        if (userRepo.existsByEmail(dto.getEmail())) {
            return new MessageDto(HttpStatus.BAD_REQUEST, "Email allready exists");
        }

        String password = passwordEncoder.encode(dto.getPassword());
        List<String> rolesDefault = Arrays.asList("ROLE_ADMIN","ROLE_USER");
        dto.setRoles(rolesDefault);

        try {
            List<RolEnums> roles = dto.getRoles().stream().map(rol -> RolEnums.valueOf(rol)).collect(Collectors.toList());

            User user = new User(null, dto.getName(), dto.getUsername(), dto.getEmail(), password, dto.getAvatar(), roles);

            userRepo.save(user);
        } catch (Exception e) {
            return new MessageDto(HttpStatus.BAD_REQUEST,"Invalid roles");
        }

        return new MessageDto(HttpStatus.CREATED, "User successfully added");
    }

    @Override
    public TokenDto login(LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JwtProvider.generateToken(authentication);

        return new TokenDto(token);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> entities = userRepo.findAll();
        List<UserDto> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(modelMapper.map(entity, UserDto.class)));

        return dtos;

    }

    public TokenDto validate(String token) {
        if(!JwtProvider.validateToken(token))
            return null;
        String username = JwtProvider.getUsernameFromToken(token);
        if(!userRepo.findUserByUsernameOrEmail(username,username).isPresent())
            return null;
        return new TokenDto(token);
    }

}
