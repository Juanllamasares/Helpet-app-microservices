package com.helpet.commentservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpet.commentservice.dto.CreateCommentDto;
import com.helpet.commentservice.dto.RequestCommentDto;
import com.helpet.commentservice.service.CommentServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping("/create")
    public ResponseEntity<String> createComment(@Valid @RequestBody CreateCommentDto createCommentDto) {
        commentService.createComment(createCommentDto);
        return new ResponseEntity<>("Comment successfully be created.", HttpStatus.CREATED);
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<Map<String, Object>> updateComment(@PathVariable(name = "commentId") Long commentId,
            @RequestBody CreateCommentDto commentDto) {
        if (commentService.findCommentById(commentId) == null)
            return ResponseEntity.notFound().build();
        commentService.updateComment(commentId, commentDto);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "comment successfully be updated");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RequestCommentDto>> getComments(){
        List<RequestCommentDto> commentsDtos = commentService.getComments();
        return ResponseEntity.ok(commentsDtos);
    }

}
