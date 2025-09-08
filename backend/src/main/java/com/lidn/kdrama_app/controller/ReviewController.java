package com.lidn.kdrama_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lidn.kdrama_app.dto.ReviewDto;
import com.lidn.kdrama_app.service.reviewServices.ReviewService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/review")
public class ReviewController {
    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public List<ReviewDto> getReviews() {
        return reviewService.getReviews();
    }

    @GetMapping("/user/{userId}/drama/{dramaId}")
    public ReviewDto getReview(@PathVariable Long userId, @PathVariable Long dramaId) {
        return reviewService.getReview(userId, dramaId);
    }
    
    @PostMapping("/")
    public ReviewDto createReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto saved = reviewService.creatReview(reviewDto);
        return saved;
    }

    @PutMapping("/user/{userId}/drama/{dramaId}")
    public ReviewDto updateReview(@PathVariable Long userId, @PathVariable Long dramaId, @RequestBody ReviewDto reviewDto) {
        ReviewDto saved = reviewService.updateReview(userId, dramaId, reviewDto);
        return saved;
    }

    @DeleteMapping("/user/{userId}/drama/{dramaId}")
    public void deleteReview(@PathVariable Long userId, @PathVariable Long dramaId) {
        reviewService.deleteReview(userId, dramaId);
    }
    
}
