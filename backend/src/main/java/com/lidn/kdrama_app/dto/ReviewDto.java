package com.lidn.kdrama_app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDto {
    private Long user_id;
    private Long drama_id;
    private String reviewText;
    private int rating;
}
