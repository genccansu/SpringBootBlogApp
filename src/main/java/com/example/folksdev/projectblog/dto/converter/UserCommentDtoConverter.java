package com.example.folksdev.projectblog.dto.converter;

import com.example.folksdev.projectblog.dto.UserCommentDto;
import com.example.folksdev.projectblog.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class UserCommentDtoConverter {
    public UserCommentDto convert(Comment from){
        return new UserCommentDto(
                from.getId(),
                from.getBody(),
                from.getCreationDate()
        );



    }
}
