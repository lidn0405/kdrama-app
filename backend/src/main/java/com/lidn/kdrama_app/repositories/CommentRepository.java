package com.lidn.kdrama_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lidn.kdrama_app.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
