package com.lidn.kdrama_app.models;

import com.lidn.kdrama_app.models.reviews.Review;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private User user;
    private Review review;
    private Long parentId;
    private String commentText;
    private int votes;
}
