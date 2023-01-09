package com.helpet.commentservice.service;

import java.util.ArrayList;
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
        Comment commentEntity = modelMapper.map(commentDto,Comment.class);
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
        List<Comment> commentEntities = commentRepo.findByPostId(id);
        List<RequestCommentDto> dtos = new ArrayList<>();
        commentEntities.forEach(entity -> dtos.add(modelMapper.map(entity, RequestCommentDto.class)));
        return dtos;
    }
    
}
