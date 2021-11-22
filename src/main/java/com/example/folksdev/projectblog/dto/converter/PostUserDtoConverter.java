package com.example.folksdev.projectblog.dto.converter;

import com.example.folksdev.projectblog.dto.PostUserDto;
import com.example.folksdev.projectblog.model.User;
import org.springframework.stereotype.Component;

@Component
public class PostUserDtoConverter {
    public PostUserDto convert(User from) {
        return new PostUserDto(
                from.getId(),
                from.getName(),
                from.getLastName(),
                from.getEmail(),
                from.getDisplayName()
        );
    }
}
