package com.helpet.authservice.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpet.authservice.security.entity.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long>{
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<AuthUser> findByUsernameOrEmail(String username,String email);
}
