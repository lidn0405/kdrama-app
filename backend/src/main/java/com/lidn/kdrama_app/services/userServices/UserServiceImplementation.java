package com.lidn.kdrama_app.services.userServices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lidn.kdrama_app.dto.UserDto;
import com.lidn.kdrama_app.enums.Role;
import com.lidn.kdrama_app.models.Review;
import com.lidn.kdrama_app.models.User;
import com.lidn.kdrama_app.repositories.ReviewRepository;
import com.lidn.kdrama_app.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private ReviewRepository reviewRepository;

    public UserServiceImplementation(UserRepository userRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
            .map(user -> new UserDto(user))
            .collect(Collectors.toList());
    }

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No entity found with id: " + id));

        return new UserDto(user);
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        try {
            user.setRole(Role.valueOf(userDto.getRole()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role provided");
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        List<Review> userReviews = reviewRepository.findAllById(userDto.getUserReviewIds());
        user.setUserReviews(userReviews);
        UserDto savedUser = new UserDto(userRepository.save(user));

        return savedUser;
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
        
        try {
            user.setRole(Role.valueOf(userDto.getRole().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role provided");
        }

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        List<Review> userReviews = reviewRepository.findAllById(userDto.getUserReviewIds());
        user.setUserReviews(userReviews);

        UserDto savedUser = new UserDto(userRepository.save(user));
        return savedUser;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
