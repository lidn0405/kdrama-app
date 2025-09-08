package com.lidn.kdrama_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lidn.kdrama_app.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
