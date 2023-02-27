package com.helpet.commentservice.service;


import java.util.List;

import com.helpet.commentservice.dto.CreateCommentDto;
import com.helpet.commentservice.dto.RequestCommentDto;

public interface ICommentService {
    
    public void createComment(CreateCommentDto commentDto);

    public void updateComment(Long id, CreateCommentDto commentDto);

    public void deleteComment(Long id);

    public List<RequestCommentDto> findByPostId(Long id);

    public RequestCommentDto findCommentById(Long id);

    public List<RequestCommentDto> getComments();

    public List<RequestCommentDto> getCommentsByUser(Long userId);

    public void deleteCommentsByPostId(Long id);

    public void deleteCommentsByUserId(Long id);

}
