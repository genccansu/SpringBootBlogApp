package com.example.folksdev.projectblog.dto.converter;

import com.example.folksdev.projectblog.dto.UserPostDto;
import com.example.folksdev.projectblog.model.Post;
import org.springframework.stereotype.Component;

@Component
public class UserPostDtoConverter {

    public UserPostDto convert(Post from){
        return new UserPostDto(
                from.getId(),
                from.getTitle(),
                from.getBody(),
                from.getCreationDate()

        );
    }
}
