package com.helpet.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpet.authservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findUserByUsernameOrEmail(String username,String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
