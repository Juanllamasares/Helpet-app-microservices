package com.helpet.commentservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RequestCommentDto {
    
    private Long id;
    private Date date;
    private String content;
    private Long post;
    private Long user;
}
