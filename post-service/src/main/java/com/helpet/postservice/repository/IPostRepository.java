package com.helpet.postservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpet.postservice.entity.Post;



@Repository
public interface IPostRepository extends JpaRepository<Post, Long>{
    List<Post> findByLocation(String location);
}
