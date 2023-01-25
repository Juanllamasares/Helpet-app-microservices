package com.helpet.postservice.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "tbl_posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date date;

    @NotNull
    @Size(min = 2, max = 100, message = "The status must have a minimum of 2 and a maximum of 100 characters.")
    private String status;

    @NotNull
    @Size(min = 5, max = 2000, message = "The description must have a minimum of 5 and a maximum of 2000 characters.")
    private String description;

    @NotNull
    @Size(min = 1, max = 500, message = "The image URL must have a minimum of 1 and a maximum of 500 characters.")
    private String img;

    @NotNull
    @Size(min = 5, max = 200, message = "The location must have a minimum of 5 and a maximum of 200 characters.")
    private String location;

    @NotNull
    @Column(name = "user_id")
    private Long user;
}