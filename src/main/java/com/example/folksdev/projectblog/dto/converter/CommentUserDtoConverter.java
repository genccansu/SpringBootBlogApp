package com.example.folksdev.projectblog.dto.converter;

import com.example.folksdev.projectblog.dto.CommentUserDto;
import com.example.folksdev.projectblog.model.User;
import org.springframework.stereotype.Component;

@Component
public class CommentUserDtoConverter {
    public CommentUserDto convert(User from) {
        return new CommentUserDto(
                from.getId(),
                from.getName(),
                from.getLastName(),
                from.getDisplayName(),
                from.getGender()

        );
    }
}
