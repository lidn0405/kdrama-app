package com.lidn.kdrama_app.models.reviews;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "drama_id")
    private Long dramaId;
}
