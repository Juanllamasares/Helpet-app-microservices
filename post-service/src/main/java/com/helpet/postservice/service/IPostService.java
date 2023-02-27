package com.helpet.postservice.service;

import java.util.List;

import com.helpet.postservice.dto.CreatePostDto;
import com.helpet.postservice.dto.PostDto;


public interface IPostService {
    
    public boolean createPost(CreatePostDto createPostDto);

    public void deletePost(Long id);

    public List<PostDto> getPosts();

    public PostDto getPostById(Long id);

    public List<PostDto> getPostsByLocation(String location);

    public void updatePost(Long id, PostDto postDto);

    public void deletePostsByUserId(Long userId);

    public List<PostDto> getPostsByUser(Long userId);

}
