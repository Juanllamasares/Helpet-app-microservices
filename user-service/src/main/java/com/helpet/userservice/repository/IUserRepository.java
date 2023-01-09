package com.helpet.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpet.userservice.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
    Optional<com.helpet.userservice.entity.User> findByUsername(String username);
    boolean existsByUsername (String username);
    boolean existsByEmail (String email);
}
