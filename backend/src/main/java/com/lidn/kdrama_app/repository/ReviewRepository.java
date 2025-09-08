package com.lidn.kdrama_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lidn.kdrama_app.entity.reviews.Review;
import com.lidn.kdrama_app.entity.reviews.ReviewKey;

public interface ReviewRepository extends JpaRepository<Review, ReviewKey>{
    
}
