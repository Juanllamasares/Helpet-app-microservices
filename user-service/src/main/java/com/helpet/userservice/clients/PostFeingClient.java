package com.helpet.userservice.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.helpet.userservice.models.Post;

@FeignClient(name = "post-service")
public interface PostFeingClient {
    @RequestMapping(method = RequestMethod.DELETE, value = "/api/posts/delete-by-user/{userId}")
    public void deletePostsByUser(@PathVariable("userId") Long id);

    @RequestMapping(method = RequestMethod.GET,value = "/api/posts/user/{userId}")
    public List<Post> getPostsByUser(@PathVariable("userId") Long id);

}
