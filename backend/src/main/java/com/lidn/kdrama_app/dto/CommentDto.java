package com.lidn.kdrama_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private Long id;
    private Long userId;
    private Long reviewUserId;
    private Long reviewDramaId;
    private Long parentId;
    private String commentText;
    private int votes;
}
