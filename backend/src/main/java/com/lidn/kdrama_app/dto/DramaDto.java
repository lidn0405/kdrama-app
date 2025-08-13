package com.lidn.kdrama_app.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.lidn.kdrama_app.models.Drama;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DramaDto {
    @Id
    private Long id;
    private String name;
    private String description;
    private List<Long> dramaReviews;

    public DramaDto(Drama drama) {
        this.id = drama.getId();
        this.name = drama.getName();
        this.description = drama.getDescription();
        this.dramaReviews = drama.getDramaReviews().stream()
            .map((review) -> review.getId())
            .collect(Collectors.toList());
    }
}
