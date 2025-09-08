package com.lidn.kdrama_app.service.userServices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lidn.kdrama_app.dto.UserDto;
import com.lidn.kdrama_app.entity.User;
import com.lidn.kdrama_app.entity.reviews.Review;
import com.lidn.kdrama_app.entity.reviews.ReviewKey;
import com.lidn.kdrama_app.enums.Role;
import com.lidn.kdrama_app.mapper.UserMapper;
import com.lidn.kdrama_app.repository.ReviewRepository;
import com.lidn.kdrama_app.repository.UserRepository;

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
            .map(user -> UserMapper.toDto(user))
            .collect(Collectors.toList());
    }

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No entity found with id: " + id));

        return UserMapper.toDto(user);
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
        user.setPictureUrl(userDto.getPictureUrl());
        UserDto savedUser = UserMapper.toDto(userRepository.save(user));

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
        user.setPictureUrl(userDto.getPictureUrl());
        UserDto savedUser = UserMapper.toDto(userRepository.save(user));
        return savedUser;
    }

    // TODO: Create update service to update user's reviews
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto getByGoogleId(String googleId) {
        User user = userRepository.findByGoogleId(googleId)
            .orElseThrow(() -> new EntityNotFoundException("No user found with google id: " + googleId));

        return UserMapper.toDto(user);
    }

    @Transactional
    public UserDto processOAuthPostLogin(String googleId, String username, String email, String pictureUrl) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setUsername(username);
            user.setPictureUrl(pictureUrl);
            user.setGoogleId(googleId);
            System.out.println("Updating existing user: " + email);
        } else {
            User newUser = new User();
            newUser.setRole(Role.USER);
            newUser.setGoogleId(googleId);
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPictureUrl(pictureUrl);
            user = newUser;
            System.out.println("Creating new user: " + email);
        }
        User savedUser = userRepository.save(user);
        System.out.println("User successfully saved/updated in the database: " + email);
        
        return UserMapper.toDto(savedUser);
    }
}
