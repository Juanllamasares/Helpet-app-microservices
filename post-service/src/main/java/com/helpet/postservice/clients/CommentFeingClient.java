package com.helpet.postservice.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.helpet.postservice.models.Comment;

@FeignClient(name = "comment-service", url = "http://localhost:8082/api/comments")
public interface CommentFeingClient {
    
    @DeleteMapping("/delete-by-post-id/{postId}")
    public void deleteCommentsByPostId(@PathVariable("postId") Long postId);

    @GetMapping("/comments-by-user-id/{userId}")
    public List<Comment> getCommentsByUserId(@PathVariable("userId") Long userId);
}
