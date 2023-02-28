package com.helpet.postservice.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpet.postservice.dto.CreatePostDto;
import com.helpet.postservice.dto.PostDto;
import com.helpet.postservice.entity.Post;
import com.helpet.postservice.repository.IPostRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createPost(CreatePostDto createPostDto) {
        Post postEntity = modelMapper.map(createPostDto, Post.class);
        postEntity.setDate(new Date());
        postRepo.save(postEntity);
    }

    @Override
    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }

    @Override
    public List<PostDto> getPosts() {
        List<Post> postsEntities = postRepo.findAll();
        List<PostDto> postsDto = new LinkedList<>();

        for (Post entity : postsEntities) {
            postsDto.add(0, modelMapper.map(entity, PostDto.class));
        }

        return postsDto;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post postEntity = postRepo.findById(id).orElse(null);
        if(postEntity == null) return null;
        PostDto postDto = modelMapper.map(postEntity, PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> getPostsByLocation(String location) {
        List<Post> postsEntities = postRepo.findByLocation(location);
        List<PostDto> postsDto = new LinkedList<>();

        for (Post entity : postsEntities) {
            postsDto.add(0, modelMapper.map(entity, PostDto.class));
        }

        return postsDto;
    }

    @Override
    public void updatePost(Long id, PostDto postDto) {
        Post postEntity = postRepo.findById(id).get();

        postEntity.setDate(postDto.getDate());
            postEntity.setStatus(postDto.getStatus());
            postEntity.setDescription(postDto.getDescription());
            postEntity.setLocation(postDto.getLocation());
            postEntity.setImg(postDto.getImg());

            postRepo.save(postEntity);
    }

    @Override
    public void deletePostsByUserId(Long userId) {
        postRepo.deleteByUser(userId);
    
    }

    @Override
    public List<PostDto> getPostsByUser(Long userId) {
        List<Post> postsEntities = postRepo.findByUser(userId);
        List<PostDto> dtos = new LinkedList<>();
        postsEntities.forEach(entity-> dtos.add(0, modelMapper.map(entity, PostDto.class)));
        return dtos;
    }

}
