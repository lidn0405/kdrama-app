package com.lidn.kdrama_app.dto;

import com.lidn.kdrama_app.models.Comment;

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

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.reviewUserId = comment.getReview().getId().getUserId();
        this.reviewDramaId = comment.getReview().getId().getDramaId();
        this.parentId = comment.getParentId();
        this.commentText = comment.getCommentText();
        this.votes = comment.getVotes();
    }
}
