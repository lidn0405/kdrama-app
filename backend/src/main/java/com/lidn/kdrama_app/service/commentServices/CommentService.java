package com.lidn.kdrama_app.service.commentServices;

import java.util.List;

import com.lidn.kdrama_app.dto.CommentDto;

public interface CommentService {
    List<CommentDto> getComments();
    CommentDto getComment(Long id);
    CommentDto updateComment(Long id, CommentDto commentDto);
    CommentDto createComment(CommentDto commentDto);
    void deleteComment(Long id);
}
