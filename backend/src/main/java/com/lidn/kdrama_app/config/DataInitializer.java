package com.lidn.kdrama_app.config;

import org.springframework.stereotype.Component;

import com.lidn.kdrama_app.entity.Comment;
import com.lidn.kdrama_app.entity.Drama;
import com.lidn.kdrama_app.entity.User;
import com.lidn.kdrama_app.entity.reviews.Review;
import com.lidn.kdrama_app.enums.Role;
import com.lidn.kdrama_app.repository.CommentRepository;
import com.lidn.kdrama_app.repository.DramaRepository;
import com.lidn.kdrama_app.repository.ReviewRepository;
import com.lidn.kdrama_app.repository.UserRepository;

@Component
public class DataInitializer implements org.springframework.boot.CommandLineRunner {
    private UserRepository userRepository;
    private DramaRepository dramaRepository;
    private ReviewRepository reviewRepository;
    private CommentRepository commentRepository;

    public DataInitializer(UserRepository userRepository, DramaRepository dramaRepository, ReviewRepository reviewRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.dramaRepository = dramaRepository;
        this.reviewRepository = reviewRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        User user1 = null;
        if (userRepository.count() == 0) {
            user1 = new User(Role.USER, "", "User1", "user1@gmail.com", "userahaha");
            userRepository.save(user1);
        }
        Drama drama1 = null;
        if (dramaRepository.count() == 0) {
            drama1 = new Drama("Drama1", "This is drama 1");
            dramaRepository.save(drama1);
        }
        Review review1 = null;
        if (reviewRepository.count() == 0) {
            review1 = new Review(user1, drama1, "Review Text 1", 5);
            reviewRepository.save(review1);
        }
        Comment comment1 = null;
        if (commentRepository.count() == 0) {
            comment1 = new Comment(user1, review1, null, "Comment Text 1", 0);
            commentRepository.save(comment1);
        }
    }
}
