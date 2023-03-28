package com.helpet.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class NewUserDto {

    @Size(min = 2,max = 50)
    @NotBlank(message = "Name is mandatory,min 2 and max 50 characters")
    private String name;

    @Size(min = 2,max = 50)
    @NotBlank(message = "Username is mandatory,min 2 and max 50 characters")
    private String username;

    @Email(message = "Invalid email")
    private String email;
    
    @Size(min = 5,max = 50)
    @NotBlank(message = "Password is mandatory,min 5 and max 50 characters")
    private String password;
    
    private String avatar;
}
