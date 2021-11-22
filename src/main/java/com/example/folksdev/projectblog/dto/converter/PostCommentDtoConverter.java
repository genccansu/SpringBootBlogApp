package com.example.folksdev.projectblog.dto.converter;

import com.example.folksdev.projectblog.dto.PostCommentDto;
import com.example.folksdev.projectblog.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class PostCommentDtoConverter {
    public PostCommentDto convert(Comment from) {
        return new PostCommentDto(
                from.getId(),
                from.getBody(),
                from.getCreationDate()
        );
    }

}
