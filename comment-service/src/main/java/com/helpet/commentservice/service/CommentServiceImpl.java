package com.helpet.commentservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpet.commentservice.dto.CreateCommentDto;
import com.helpet.commentservice.dto.RequestCommentDto;
import com.helpet.commentservice.entity.Comment;
import com.helpet.commentservice.repository.ICommentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements ICommentService{

    @Autowired
    private ICommentRepository commentRepo;

    @Autowired
    private ModelMapper modelMapper;
    

    @Override
    public void createComment(CreateCommentDto commentDto) {
        commentDto.setDate(new Date());
        Comment commentEntity = modelMapper.map(commentDto, Comment.class);
        commentRepo.save(commentEntity);
    }

    @Override
    public void updateComment(Long id, CreateCommentDto commentDto) {
        Comment commentEntity = commentRepo.findById(id).get();
        commentEntity.setContent(commentDto.getContent());
        commentRepo.save(commentEntity);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public List<RequestCommentDto> findByPostId(Long id) {
        List<Comment> commentEntities = commentRepo.findByPost(id);
        List<RequestCommentDto> dtos = new ArrayList<>();
        commentEntities.forEach(entity -> dtos.add(modelMapper.map(entity, RequestCommentDto.class)));
        return dtos;
    }

    @Override
    public RequestCommentDto findCommentById(Long id) {
        if(commentRepo.findById(id)==null) return null;
        RequestCommentDto dto = modelMapper.map(commentRepo.findById(id), RequestCommentDto.class);
        return dto;
    }

    @Override
    public List<RequestCommentDto> getComments() {
        List<Comment> commentsEntities = commentRepo.findAll();
        List<RequestCommentDto> dtos = new ArrayList<>();
        commentsEntities.forEach(entity -> dtos.add(modelMapper.map(entity, RequestCommentDto.class)));
        return dtos;
    }

    @Override
    public void deleteCommentsByPostId(Long id) {
        commentRepo.deleteByPost(id);
    }

    @Override
    public List<RequestCommentDto> getCommentsByUser(Long userId) {
       List<Comment> entities = commentRepo.findByUser(userId);
       List<RequestCommentDto> dtos = new LinkedList<>();
       entities.forEach(entity -> dtos.add(0, modelMapper.map(entity, RequestCommentDto.class)));
       return dtos;
    }

    @Override
    public void deleteCommentsByUserId(Long id) {
        commentRepo.deleteByUser(id);
    }
    
}
