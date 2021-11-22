package com.example.folksdev.projectblog.repository;

import com.example.folksdev.projectblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,String> {
}
