package com.helpet.postservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.helpet.postservice.models.User;

@FeignClient(name = "user-service", url = "http://localhost:8080/api/users")
public interface PostFeingClient {
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id);
}
