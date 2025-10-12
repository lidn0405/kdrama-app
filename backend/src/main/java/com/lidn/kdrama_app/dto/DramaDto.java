package com.lidn.kdrama_app.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DramaDto {
    private Long id;
    private String name;
    private String description;
    // Contains user ids for composite key
    private List<Long> userIdReviews;

}
