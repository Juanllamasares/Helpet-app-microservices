package com.helpet.authservice.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class NewUserDto {

    @Size(min = 2,max = 50,message = "Name: min 2 and max 50 characters")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Size(min = 2,max = 50, message = "Username: min 2 and max 50 characters")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Email(message = "Invalid email")
    private String email;
    
    @Size(min = 5,max = 50,message = "Password: min 5 and max 50 characters")
    @NotBlank(message = "Password is mandatory")
    private String password;
    
    private String avatar;

    /* @NotEmpty(message = "Roles not be empty")
    @Size(min = 1, max = 2, message = "Min one and max two roles are permited") */
    private List<String> roles = new ArrayList<>();
}
