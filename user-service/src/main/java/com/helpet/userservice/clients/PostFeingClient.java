package com.helpet.userservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "post-service", url = "http://localhost:8081/api/posts")
public interface PostFeingClient {
    @DeleteMapping("/delete-user/{userId}")
    public void deletePostsByUser(@PathVariable("userId") Long id);

}
