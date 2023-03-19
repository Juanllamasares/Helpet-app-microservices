package com.helpet.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpet.userservice.dto.RequestUserDto;
import com.helpet.userservice.clients.PostFeingClient;
import com.helpet.userservice.dto.CreateUserDto;
import com.helpet.userservice.service.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PostFeingClient postClient;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserDto userDto) {
        if (userService.existsByEmail(userDto.getEmail()))
            return new ResponseEntity<>("The entered email already exists.", HttpStatus.CONFLICT);
        if (userService.existsByUsername(userDto.getUsername()))
            return new ResponseEntity<>("The entered username already exists.", HttpStatus.CONFLICT);
        userService.createUser(userDto);
        return new ResponseEntity<>("User successfully created.", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RequestUserDto>> getUsers() {
        List<RequestUserDto> usersDtos = userService.getUsers();
        if (usersDtos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usersDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestUserDto> getUserById(@PathVariable("id") Long id) {
        RequestUserDto userDto = userService.getUserById(id);
        if (userDto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/delete/{id}")
    @CircuitBreaker(name = "postsCB",fallbackMethod = "fallBackGetPostsByUser")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        try { 
            if (userService.getUserById(id) == null)
                return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);

            if(postClient.getPostsByUser(id).isEmpty()) userService.deleteUser(id);

            if(!postClient.getPostsByUser(id).isEmpty()) {
                postClient.deletePostsByUser(id);
                userService.deleteUser(id);
            }

            return new ResponseEntity<>("User be successfully deleted.", HttpStatus.OK);

        } catch (Exception e) { 
            throw new RuntimeException(); 
        } 

    }

    public ResponseEntity<String> fallBackGetPostsByUser(RuntimeException e){
        return new ResponseEntity<>("Post service: Something went wrong, try after some time.", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId") Long id, @Valid @RequestBody CreateUserDto userDto){
        if(userService.getUserById(id) == null) return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        userService.updateUser(id, userDto);
        return new ResponseEntity<>("User successfully updated.",HttpStatus.OK);
    }
}
