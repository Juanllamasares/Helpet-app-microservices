package com.helpet.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "tbl_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The name should not be null")
    @Size(min = 2,max = 100,message = "The name should have a minimum of 2 and a maximum of 100 characters")
    private String name;

    @Column(unique = true)
    @NotNull(message = "The email should not be null")
    @Email(message = "The email should be valid")
    private String email;

    @Column(name = "user_name",unique = true)
    @NotNull(message = "The username should not be null")
    @Size(min = 2,max = 30,message = "The username should have a minimum of 2 and a maximum of 30 characters")
    private String username;

    @NotNull(message = "The password should not be null")
    @Size(min = 5,max = 50,message = "For security purposes, the password should have a minimum of 5 and a maximum of 50 characters")
    private String password;
    
}