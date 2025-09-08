package com.lidn.kdrama_app.dto;

import java.util.List;
import com.lidn.kdrama_app.enums.Role;


public class UserDto {
    private Long id;
    private String role;
    private String username;
    private String email;
    private String googleId;
    private String pictureUrl;
    // Contains drama ids for composite key
    private List<Long> reviewIds;
    private List<Long> commentIds;

    public UserDto() {}

    public Long getId() {
        return this.id;
    }

    public String getRole() {
        return this.role;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getGoogleId() {
        return this.googleId;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public List<Long> getReviews() {
        return this.reviewIds;
    }

    public List<Long> getComments() {
        return this.commentIds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        try {
            // Error if invalid role
            Role.valueOf(role.toUpperCase());
            this.role = role;
        } catch (Exception e) {
            System.out.println("Error setting role");
            throw new IllegalArgumentException("Role not found with name: " + role);
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setReviews(List<Long> reviewIds) {
        this.reviewIds = reviewIds;
    }

    public void setComments(List<Long> commentIds) {
        this.commentIds = commentIds;
    }

}
