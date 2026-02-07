package com.lidn.kdrama_app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.lidn.kdrama_app.dto.UserDto;
import com.lidn.kdrama_app.entity.User;
import com.lidn.kdrama_app.enums.Role;

public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setGoogleId(user.getGoogleId());
        userDto.setPictureUrl(user.getPictureUrl());
        List<Long> reviews = user.getUserReviews()
            .stream()
            .map((review) -> review.getId().getDramaId())
            .collect(Collectors.toList());
        userDto.setReviews(reviews);
        List<Long> comments = user.getUserComments()
            .stream()
            .map((comment) -> comment.getId())
            .collect(Collectors.toList());
        userDto.setComments(comments);
        
        return userDto;
    }

    // Should not be used really 
    public static User toEntity(UserDto userDto) {
        User newUser = new User();

        newUser.setGoogleId(userDto.getGoogleId());
        newUser.setRole(Role.ROLE_USER);
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPictureUrl(userDto.getPictureUrl());

        return newUser;
        // TODO: REMEMBER HOW TO ADD USER ARRAYLIST PROPS TO ENTITY
    }
}
