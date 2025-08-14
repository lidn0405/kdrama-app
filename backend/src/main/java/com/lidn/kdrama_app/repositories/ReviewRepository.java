package com.lidn.kdrama_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lidn.kdrama_app.models.reviews.Review;
import com.lidn.kdrama_app.models.reviews.ReviewKey;

public interface ReviewRepository extends JpaRepository<Review, ReviewKey>{
    
}
