package com.lidn.kdrama_app.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.lidn.kdrama_app.enums.Role;
import com.lidn.kdrama_app.models.User;


public class UserDto {
    private Long id;
    private String role;
    private String username;
    private String email;
    private String googleId;
    private String pictureUrl;
    // Contains drama ids for composite key
    private List<Long> dramaReviews;

    public UserDto() {}

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.googleId = user.getGoogleId();
        this.pictureUrl = user.getPictureUrl();
        this.dramaReviews = user.getUserReviews().stream()
            .map((review) -> review.getId().getDramaId())
            .collect(Collectors.toList());
    }

    // TODO: make entity swappers for dtos and entities

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

    public List<Long> getDramaReviews() {
        return this.dramaReviews;
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

    public void setUserReviewIds(List<Long> dramaReviews) {
        this.dramaReviews = dramaReviews;
    }

}
