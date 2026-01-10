package com.lidn.kdrama_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lidn.kdrama_app.dto.CommentDto;
import com.lidn.kdrama_app.service.commentServices.CommentService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/")
    public List<CommentDto> getComments() {
        return commentService.getComments();
    }

    @GetMapping("/{id}")
    public CommentDto getComment(@PathVariable Long id) {
        return commentService.getComment(id);
    }

    @PostMapping("/")
    public CommentDto createComment(@RequestBody CommentDto commentDto) {
        CommentDto saved = commentService.createComment(commentDto);
        return saved;
    }

    @PutMapping("/{id}")
    public CommentDto updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        CommentDto saved = commentService.updateComment(id, commentDto);
        return saved;
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
    
    
    
}
