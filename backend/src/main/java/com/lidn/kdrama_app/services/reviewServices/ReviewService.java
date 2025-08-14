package com.lidn.kdrama_app.services.reviewServices;

import java.util.List;

import com.lidn.kdrama_app.dto.ReviewDto;

public interface ReviewService {
    List<ReviewDto> getReviews();
    ReviewDto getReview(Long userId, Long dramaId);
    ReviewDto updateReview(Long userId, Long dramaId, ReviewDto reviewDto);
    ReviewDto creatReview(ReviewDto reviewDto);
    void deleteReview(Long userId, Long dramaId);
}
