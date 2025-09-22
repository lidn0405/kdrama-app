package com.lidn.kdrama_app.mapper;

import com.lidn.kdrama_app.dto.CommentDto;
import com.lidn.kdrama_app.entity.Comment;

// public CommentDto(Comment comment) {
//         this.id = comment.getId();
//         this.userId = comment.getUser().getId();
//         this.reviewUserId = comment.getReview().getId().getUserId();
//         this.reviewDramaId = comment.getReview().getId().getDramaId();
//         this.parentId = comment.getParentId();
//         this.commentText = comment.getCommentText();
//         this.votes = comment.getVotes();
//     }

public class CommentMapper {
    public static CommentDto toDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setUserId(comment.getUser().getId());
        dto.setReviewUserId(comment.getReview().getId().getUserId());
        dto.setReviewDramaId(comment.getReview().getId().getDramaId());
        dto.setParentId(comment.getParentId());
        dto.setCommentText(comment.getCommentText());
        dto.setVotes(comment.getVotes());

        return dto;
    }
}
