package com.helpet.authservice.entity;

import java.util.List;

import com.helpet.authservice.enums.RolEnums;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tbl_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(unique = true,length = 50)
    private String username;

    @Column(unique = true,length = 100)
    private String email;

    @Column(length = 1000)
    private String password;

    @Column(length = 500)
    private String avatar;

    private List<RolEnums> roles;
}
