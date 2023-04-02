package com.helpet.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class LoginDto {
    
    @Size(min = 2,max = 50, message = "Username: min 2 and max 50 characters")
    @NotBlank(message = "Username is mandatory")
    private String username;
    
    @Size(min = 5,max = 50,message = "Password: min 5 and max 50 characters")
    @NotBlank(message = "Password is mandatory")
    private String password;
}
