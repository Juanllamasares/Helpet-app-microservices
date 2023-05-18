package com.helpet.authservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserDto {
    
    private Long id;

    private String name;

    private String email;
    
    private String username;

    private String avatar;

    private List<String> roles;
}
