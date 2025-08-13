package com.lidn.kdrama_app.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.BeanDefinitionDsl.Role;

import com.lidn.kdrama_app.models.User;

import jakarta.persistence.Id;

public class UserDto {
    @Id
    private Long id;
    private String role;
    private String username;
    private String email;
    private String password;
    private List<Long> userReviewIds;

    public UserDto() {}

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userReviewIds = user.getUserReviews().stream()
            .map((review) -> review.getId())
            .collect(Collectors.toList());
    }

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

    public String getPassword() {
        return this.password;
    }

    public List<Long> getUserReviewIds() {
        return this.userReviewIds;
    }

    public void setRole(String role) {
        try {
            // Error if invalid role
            Role.valueOf(role.toUpperCase());
            this.role = role;
        } catch (Exception e) {
            System.out.println("Error setting role");
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserReviewIds(List<Long> userReviewIds) {
        this.userReviewIds = userReviewIds;
    }

}
