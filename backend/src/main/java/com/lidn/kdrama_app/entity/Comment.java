package com.lidn.kdrama_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lidn.kdrama_app.entity.reviews.Review;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "review_user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "review_drama_id", referencedColumnName = "drama_id")
    })
    @JsonBackReference
    private Review review;
    private Long parentId;
    private String commentText;
    private int votes;

    public Comment(User user, Review review, Long parentId, String commentText, int votes) {
        this.user = user;
        this.review = review;
        this.parentId = parentId;
        this.commentText = commentText;
        this.votes = votes;
    }
}
