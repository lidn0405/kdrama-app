package com.lidn.kdrama_app.mapper;

import java.util.stream.Collectors;

import com.lidn.kdrama_app.dto.DramaDto;
import com.lidn.kdrama_app.entity.Drama;

public class DramaMapper {
    public static DramaDto toDto(Drama drama) {
        DramaDto dto = new DramaDto();

        dto.setId(drama.getId());
        dto.setName(drama.getName());
        dto.setDescription(drama.getDescription());
        dto.setUserIdReviews(drama.getDramaReviews().stream()
            .map(review -> review.getId().getUserId())
            .collect(Collectors.toList()));

        return dto;
    }

    // Only used during creation/not really used
    public static Drama toEntity(DramaDto dto) {
        Drama drama = new Drama();
        drama.setName(dto.getName());
        drama.setDescription(dto.getDescription());

        return drama;
    }
}
