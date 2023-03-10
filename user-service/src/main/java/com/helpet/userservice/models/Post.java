package com.helpet.userservice.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Post {
    
    private Long id;
    private Date date;
    private String content;
}
