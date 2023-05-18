package com.helpet.authservice.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class NewUserDto {
    
    private Long id;

    @NotBlank(message = "name is required")
    @Size(min = 2,max = 50,message = "Name: min 2, max 50 caracters required")
    private String name;
    @NotBlank(message = "email is required")
    @Email(message = "invalid email")
    private String email;
    @NotBlank(message = "username is required")
    @Size(min = 2,max = 50,message = "Username: min 2, max 50 caracters required")
    private String username;
    @NotBlank(message = "password is required")
    @Size(min = 5,max = 50,message = "Password: min 5, max 50 caracters required")
    private String password;

    private String avatar;

    List<String> roles;
}
