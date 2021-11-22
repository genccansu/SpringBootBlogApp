package com.example.folksdev.projectblog.dto.converter;

import com.example.folksdev.projectblog.dto.UserDto;
import com.example.folksdev.projectblog.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    private final UserPostDtoConverter userPostDtoConverter;
    private final UserCommentDtoConverter userCommentDtoConverter;

    public UserDtoConverter(UserPostDtoConverter userPostDtoConverter, UserCommentDtoConverter userCommentDtoConverter) {
        this.userPostDtoConverter = userPostDtoConverter;
        this.userCommentDtoConverter = userCommentDtoConverter;
    }

    public UserDto convertToUserDto(User from){
        return new UserDto(
                from.getId(),
                from.getName(),
                from.getLastName(),
                from.getDisplayName(),
               from.getCreationDate(),
                from.getEmail(),
                from.getGender(),
                from.getPosts()
                        .stream()
                        .map(userPostDtoConverter::convert)
                        .collect(Collectors.toList()),
                from.getComments()
                        .stream()
                        .map(userCommentDtoConverter::convert)
                        .collect(Collectors.toList())
        );
    }

    public List<UserDto> convertToUserDtoList(List<User> users) {
        return users
                .stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }
}
