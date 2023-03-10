package com.helpet.commentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpet.commentservice.entity.Comment;

@Repository
public interface ICommentRepository extends JpaRepository<Comment,Long>{
    List<Comment> findByPost(Long postId);
    void deleteByPost(Long postId);
}
