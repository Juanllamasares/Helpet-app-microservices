package com.helpet.authservice.dto;

import java.util.HashSet;
import java.util.Set;

import com.helpet.authservice.enums.RolEnums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserDto {
    
    private String name;

    private String username;

    private String email;
    
    //private String password;
    
    private String avatar;

    private Set<RolEnums> roles = new HashSet<>();
}
