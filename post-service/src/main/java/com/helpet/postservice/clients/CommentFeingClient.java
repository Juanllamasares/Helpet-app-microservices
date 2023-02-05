package com.helpet.postservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "comment-service", url = "http://localhost:8082/api/comments")
public interface CommentFeingClient {
    
    @DeleteMapping("/delete-by-post-id/{postId}")
    public void deleteCommentsByPostId(@PathVariable("postId") Long postId);
}
