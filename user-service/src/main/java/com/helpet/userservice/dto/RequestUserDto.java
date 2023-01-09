package com.helpet.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class RequestUserDto {

    private Long id;
    
    private String name;

    private String email;

    private String username;
}
