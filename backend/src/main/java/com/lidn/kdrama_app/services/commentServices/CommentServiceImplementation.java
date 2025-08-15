package com.lidn.kdrama_app.services.commentServices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lidn.kdrama_app.dto.CommentDto;
import com.lidn.kdrama_app.models.Comment;
import com.lidn.kdrama_app.models.User;
import com.lidn.kdrama_app.models.reviews.Review;
import com.lidn.kdrama_app.models.reviews.ReviewKey;
import com.lidn.kdrama_app.repositories.CommentRepository;
import com.lidn.kdrama_app.repositories.ReviewRepository;
import com.lidn.kdrama_app.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CommentServiceImplementation implements CommentService{
    CommentRepository commentRepository;
    UserRepository userRepository;
    ReviewRepository reviewRepository;


    public CommentServiceImplementation(CommentRepository commentRepository, UserRepository userRepository, ReviewRepository reviewRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<CommentDto> getComments() {
        List<CommentDto> commentDtos = commentRepository.findAll().stream()
            .map((comment) -> new CommentDto(comment))
            .collect(Collectors.toList());
        
        return commentDtos;
    }

    @Override
    public CommentDto getComment(Long id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));

        return new CommentDto(comment);
    }

    @Override
    public CommentDto updateComment(Long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));

        comment.setCommentText(commentDto.getCommentText());
        comment.setVotes(commentDto.getVotes());
        Comment savedComment = commentRepository.save(comment);
        return new CommentDto(savedComment);
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment newComment = new Comment();
        User user = userRepository.findById(commentDto.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + commentDto.getUserId()));
        newComment.setUser(user);
        ReviewKey key = new ReviewKey(commentDto.getReviewUserId(), commentDto.getReviewDramaId());
        Review review = reviewRepository.findById(key)
            .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + key));
        newComment.setReview(review);
        newComment.setParentId(commentDto.getParentId());
        newComment.setCommentText(commentDto.getCommentText());
        newComment.setVotes(0);

        Comment savedComment = commentRepository.save(newComment);
        return new CommentDto(savedComment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
    
}
