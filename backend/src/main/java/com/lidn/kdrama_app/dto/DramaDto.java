package com.lidn.kdrama_app.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.lidn.kdrama_app.entity.Drama;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DramaDto {
    private Long id;
    private String name;
    private String description;
    // Contains user ids for composite key
    private List<Long> userReviews;

    public DramaDto(Drama drama) {
        this.id = drama.getId();
        this.name = drama.getName();
        this.description = drama.getDescription();
        this.userReviews = drama.getDramaReviews().stream()
            .map((review) -> review.getId().getUserId())
            .collect(Collectors.toList());
    }
}
