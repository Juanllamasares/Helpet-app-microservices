package com.helpet.authservice.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class LoginUserDto {
    @NotBlank(message = "Username is mandatory")
    @Size(min = 2, max = 30)
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 5,max = 50)
    private String password;
}
