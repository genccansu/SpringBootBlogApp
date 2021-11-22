package com.example.folksdev.projectblog.dto.converter;

import com.example.folksdev.projectblog.dto.CommentPostDto;
import com.example.folksdev.projectblog.model.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentPostDtoConverter {
    public CommentPostDto convert(Post from){
        return new CommentPostDto(
                from.getId(),
                from.getTitle(),
                from.getBody(),
                from.getCreationDate()
        );
    }
}
