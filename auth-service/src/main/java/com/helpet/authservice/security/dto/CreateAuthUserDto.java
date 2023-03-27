package com.helpet.authservice.security.dto;


import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CreateAuthUserDto {

    @NotBlank(message = "The name should not be blank")
    @Size(min = 2,max = 100,message = "The name should have a minimum of 2 and a maximum of 100 characters")
    private String name;
    
    @NotBlank(message = "The username should not be blank")
    @Size(min = 2,max = 30,message = "The username should have a minimum of 2 and a maximum of 30 characters")
    private String username;

    @NotBlank(message = "The email should not be blank")
    @Email(message = "The email should be valid")
    private String email;

    @NotBlank(message = "The password should not be blank")
    @Size(min = 5,max = 50,message = "For security purposes, the password should have a minimum of 5 and a maximum of 50 characters")
    private String password;

    @NotEmpty(message = "Roles should not be empty")
    List<String> roles = new ArrayList<>();
}
