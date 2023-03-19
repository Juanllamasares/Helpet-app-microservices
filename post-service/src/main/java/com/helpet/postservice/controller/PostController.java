package com.helpet.postservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpet.postservice.clients.UserFeingClient;
import com.helpet.postservice.dto.CreatePostDto;
import com.helpet.postservice.dto.PostDto;
import com.helpet.postservice.service.PostServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    
    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private UserFeingClient userClient;


    @PostMapping("/create")
    @CircuitBreaker(name = "usersCB",fallbackMethod = "fallBackGetUser")
    public ResponseEntity<String> createPost(@Valid @RequestBody CreatePostDto createPostDto){
        if(userClient.getUserById(createPostDto.getUser()).getStatusCode() == HttpStatus.NOT_FOUND) return ResponseEntity.notFound().build();
        postService.createPost(createPostDto);
        return new ResponseEntity<>("Post successfully created.",HttpStatus.CREATED);
    }

    public ResponseEntity<String> fallBackGetUser(RuntimeException e){
        return new ResponseEntity<>("User service error: User not found",HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id){
        if(postService.getPostById(id) == null) return new ResponseEntity<>("Post not found.",HttpStatus.BAD_REQUEST);
        postService.deletePost(id);
        return new ResponseEntity<>("Post successfully deleted.",HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getPosts(){
        List<PostDto> postsDto = postService.getPosts();
        return ResponseEntity.ok(postsDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable("postId") Long id){
        PostDto postDto = postService.getPostById(id);
        if(postService.getPostById(id) == null) return new ResponseEntity<>("Post not found.",HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<PostDto>> getPostsByLocation(@PathVariable("location") String location){
        List<PostDto> postsDto = postService.getPostsByLocation(location);
        return ResponseEntity.ok(postsDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("id") Long id){
        if(postService.getPostById(id) == null) return new ResponseEntity<>("Post not found.",HttpStatus.BAD_REQUEST);
        postService.updatePost(id, postDto);
        return ResponseEntity.ok("Post successfully updated.");

    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Long id){
        List<PostDto> posts = postService.getPostsByUser(id);
        return ResponseEntity.ok(posts);
    }
    
    @DeleteMapping("/delete-by-user/{userId}")
    public ResponseEntity<Map<String,String>> deletePostsByUser(@PathVariable("userId") Long id){
        if(postService.getPostsByUser(id)==null) return ResponseEntity.noContent().build();
        postService.deletePostsByUserId(id);
        Map<String,String> result = new HashMap<>();
        result.put("message", "Posts user: "+ id + " ,successfully be deleted.");
        return ResponseEntity.ok(result);
    }
}
