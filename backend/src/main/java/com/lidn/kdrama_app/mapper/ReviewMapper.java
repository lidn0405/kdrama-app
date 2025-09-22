package com.lidn.kdrama_app.mapper;

import com.lidn.kdrama_app.dto.ReviewDto;
import com.lidn.kdrama_app.entity.reviews.Review;

public class ReviewMapper {
    public static ReviewDto toDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setUser_id(review.getId().getUserId());
        dto.setDrama_id(review.getId().getDramaId());
        dto.setReviewText(review.getReviewText());
        dto.setRating(review.getRating());

        return dto;
    }

    // Should not be used for creating entity
    // Do it in the service
    public static Review toEntity(ReviewDto dto) {
        Review review = new Review();
        // review.setUser(null);
        return review;
    }
}
