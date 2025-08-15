package com.lidn.kdrama_app.models.reviews;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lidn.kdrama_app.models.Comment;
import com.lidn.kdrama_app.models.Drama;
import com.lidn.kdrama_app.models.User;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Review {
    @EmbeddedId
    private ReviewKey id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("dramaId")
    private Drama drama;

    @OneToMany(mappedBy = "review")
    @JsonManagedReference
    private List<Comment> reviewComments = new ArrayList<>();

    private String reviewText;
    
    @Min(1)
    @Max(10)
    private int rating;

    public Review(User user, Drama drama, String reviewText, int rating) {
        ReviewKey reviewKey = new ReviewKey(user.getId(), drama.getId());
        this.id = reviewKey;
        this.user = user;
        this.drama = drama;
        this.reviewText = reviewText;
        this.rating = rating;
    }
}
