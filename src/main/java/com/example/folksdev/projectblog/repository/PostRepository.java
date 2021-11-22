package com.example.folksdev.projectblog.repository;

import com.example.folksdev.projectblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,String> {
}
