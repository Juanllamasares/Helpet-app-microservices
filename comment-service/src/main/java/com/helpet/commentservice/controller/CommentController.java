package com.helpet.commentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpet.commentservice.dto.CreateCommentDto;
import com.helpet.commentservice.service.CommentServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    
    @Autowired
    private CommentServiceImpl commentService;

    public ResponseEntity<String> createComment(@Valid @RequestBody CreateCommentDto createCommentDto){
        commentService.createComment(createCommentDto);
        return new ResponseEntity<>("Comment successfully created.",HttpStatus.CREATED);
    }

}
