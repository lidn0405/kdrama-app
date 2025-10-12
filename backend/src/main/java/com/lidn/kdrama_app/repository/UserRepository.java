package com.lidn.kdrama_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lidn.kdrama_app.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByGoogleId(String googleId);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
