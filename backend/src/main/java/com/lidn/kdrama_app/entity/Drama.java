package com.lidn.kdrama_app.entity;

import java.util.ArrayList;
import java.util.List;

import com.lidn.kdrama_app.entity.reviews.Review;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Drama {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "drama")
    private List<Review> dramaReviews = new ArrayList<>();;

    public Drama(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
