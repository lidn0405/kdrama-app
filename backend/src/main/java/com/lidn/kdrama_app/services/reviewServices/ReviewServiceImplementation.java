package com.lidn.kdrama_app.services.reviewServices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lidn.kdrama_app.dto.ReviewDto;
import com.lidn.kdrama_app.models.Drama;
import com.lidn.kdrama_app.models.Review;
import com.lidn.kdrama_app.models.User;
import com.lidn.kdrama_app.repositories.DramaRepository;
import com.lidn.kdrama_app.repositories.ReviewRepository;
import com.lidn.kdrama_app.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewServiceImplementation implements ReviewService{
    ReviewRepository reviewRepository;
    UserRepository userRepository;
    DramaRepository dramaRepository;

    public ReviewServiceImplementation(ReviewRepository reviewRepository, 
            UserRepository userRepository, DramaRepository dramaRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.dramaRepository = dramaRepository;
    }

    @Override
    public List<ReviewDto> getReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
            .map((review) -> new ReviewDto(review))
            .collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReview(Long id) {
        Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + id));

        return new ReviewDto(review);
    }

    @Override
    public ReviewDto updateReview(Long id, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + id));

        review.setReviewText(reviewDto.getReviewText());
        Review savedReview = reviewRepository.save(review);
        return new ReviewDto(savedReview);
    }

    @Override
    public ReviewDto creatReview(ReviewDto reviewDto) {
        Review newReview = new Review();
        newReview.setReviewText(reviewDto.getReviewText());
        User user = userRepository.findById(reviewDto.getUser_id())
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + reviewDto.getUser_id()));
        newReview.setUser(user);

        Drama drama = dramaRepository.findById(reviewDto.getDrama_id())
            .orElseThrow(() -> new EntityNotFoundException("Drama not found with id: " + reviewDto.getDrama_id()));
        newReview.setDrama(drama);
        
        Review savedReview = reviewRepository.save(newReview);
        return new ReviewDto(savedReview);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
    
}
