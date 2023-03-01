package com.helpet.postservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.helpet.postservice.models.User;

@FeignClient(name = "user-service")
@RequestMapping("/api/users")
public interface UserFeingClient {
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id);
}
