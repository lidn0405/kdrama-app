package com.lidn.kdrama_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lidn.kdrama_app.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
