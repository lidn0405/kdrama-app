package com.lidn.kdrama_app.models;

import java.util.ArrayList;
import java.util.List;

import com.lidn.kdrama_app.enums.Role;
import com.lidn.kdrama_app.models.reviews.Review;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String googleId;
    private Role role;
    private String username;
    private String email;
    private String pictureUrl;

    @OneToMany(mappedBy = "user")
    private List<Review> userReviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> userComments = new ArrayList<>();

    public User() {}

    public User(Role role, String googleId, String username, String email, String pictureUrl) {
        this.googleId = googleId;
        this.role = role;
        this.username = username;
        this.email = email;
        this.pictureUrl = pictureUrl;
    }

    public Long getId() {
        return this.id;
    }

    public String getGoogleId() {
        return this.googleId;
    }

    public Role getRole() {
        return this.role;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public List<Review> getUserReviews() {
        return this.userReviews;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGoogleId(String id) {
        this.googleId = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setUserReviews(List<Review> userReviews) {
        this.userReviews = userReviews;
    }
}
