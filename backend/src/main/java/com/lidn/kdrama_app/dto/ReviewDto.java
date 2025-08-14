package com.lidn.kdrama_app.dto;

import com.lidn.kdrama_app.models.reviews.Review;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDto {
    private Long user_id;
    private Long drama_id;
    private String reviewText;
    private int rating;

    public ReviewDto(Review review) {
        this.user_id = review.getUser().getId();
        this.drama_id = review.getDrama().getId();
        this.reviewText = review.getReviewText();
        this.rating = review.getRating();
    }
}
