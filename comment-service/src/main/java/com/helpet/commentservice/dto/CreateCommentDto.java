package com.helpet.commentservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CreateCommentDto {
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date date;
    @NotNull
    @Size(min = 1,max = 500,message = "The content must have a minimum of 2 and a maximum of 100 characters.")
    private String content;
    @NotNull
    private Long postId;
    @NotNull
    private Long userId;
}
