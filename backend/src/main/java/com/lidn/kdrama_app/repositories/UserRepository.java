package com.lidn.kdrama_app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lidn.kdrama_app.models.User;


public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByGoogleId(String googleId);
    Optional<User> findByEmail(String email);
}
