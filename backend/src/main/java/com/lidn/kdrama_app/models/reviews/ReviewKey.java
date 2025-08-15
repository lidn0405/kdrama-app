package com.lidn.kdrama_app.models.reviews;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewKey implements Serializable {
    private Long userId;
    private Long dramaId;
}
