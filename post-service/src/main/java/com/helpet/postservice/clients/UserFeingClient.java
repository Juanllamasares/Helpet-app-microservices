package com.helpet.postservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.helpet.postservice.models.User;

@FeignClient(name = "user-service")
public interface UserFeingClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id);
}
