package com.example.folksdev.projectblog.dto.converter;

import com.example.folksdev.projectblog.dto.CommentDto;
import com.example.folksdev.projectblog.dto.PostDto;
import com.example.folksdev.projectblog.model.Comment;
import com.example.folksdev.projectblog.model.Post;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentDtoConverter {
    private final CommentUserDtoConverter commentUserDtoConverter;
    private final CommentPostDtoConverter commentPostDtoConverter;

    public CommentDtoConverter(CommentUserDtoConverter commentUserDtoConverter, CommentPostDtoConverter commentPostDtoConverter) {
        this.commentUserDtoConverter = commentUserDtoConverter;
        this.commentPostDtoConverter = commentPostDtoConverter;
    }

    public CommentDto convertToCommentDto(Comment from) {
        return new CommentDto(
                from.getId(),
                from.getBody(),
                LocalDate.now(),
                commentUserDtoConverter.convert(from.getUser()),
                commentPostDtoConverter.convert(from.getPosts())

                );
    }

    public List<CommentDto> convertToCommentDtoList(List<Comment> comments) {
        return comments.stream()
                .map(this::convertToCommentDto)
                .collect(Collectors.toList());
    }

}
