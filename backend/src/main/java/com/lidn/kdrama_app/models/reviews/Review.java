package com.lidn.kdrama_app.models.reviews;

import com.lidn.kdrama_app.models.Drama;
import com.lidn.kdrama_app.models.User;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
    @JoinColumn(name = "user_ids")
    private User user;

    @ManyToOne
    @MapsId("dramaId")
    @JoinColumn(name = "drama_ids")
    private Drama drama;

    private String reviewText;
    
    @Min(1)
    @Max(10)
    private int rating;
}
