package com.lidn.kdrama_app.services.reviewServices;

import java.util.List;

import com.lidn.kdrama_app.dto.ReviewDto;

public interface ReviewService {
    List<ReviewDto> getReviews();
    ReviewDto getReview(Long id);
    ReviewDto updateReview(Long id, ReviewDto reviewDto);
    ReviewDto creatReview(ReviewDto reviewDto);
    void deleteReview(Long id);
}
