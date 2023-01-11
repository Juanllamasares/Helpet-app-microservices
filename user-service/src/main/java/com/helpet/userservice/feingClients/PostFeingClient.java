package com.helpet.userservice.feingClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "post-service", url = "http://localhost:8081")
@RequestMapping("/api/posts")
public interface PostFeingClient {
    
}
