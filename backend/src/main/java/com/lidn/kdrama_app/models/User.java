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
    private Role role;
    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Review> userReviews = new ArrayList<>();

    public User() {}

    public User(Role role, String username, String email, String password) {
        this.role = role;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return this.id;
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

    public String getPassword() {
        return this.password;
    }

    public List<Review> getUserReviews() {
        return this.userReviews;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserReviews(List<Review> userReviews) {
        this.userReviews = userReviews;
    }
}
