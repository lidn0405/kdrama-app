package com.lidn.kdrama_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lidn.kdrama_app.models.Review;

public interface ReviewRepository extends JpaRepository<Long, Review>{
    
}
