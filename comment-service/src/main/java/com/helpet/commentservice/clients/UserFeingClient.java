package com.helpet.commentservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.helpet.commentservice.models.User;

@FeignClient(name = "user-service", url = "http://localhost:8080/api/users")
public interface UserFeingClient {
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id);
}
