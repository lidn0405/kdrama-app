package com.lidn.kdrama_app.dto;

import com.lidn.kdrama_app.models.Review;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDto {
    @Id
    private Long id;
    private Long user_id;
    private Long drama_id;
    private String reviewText;

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.user_id = review.getUser().getId();
        this.drama_id = review.getDrama().getId();
        this.reviewText = review.getReviewText();
    }
}
