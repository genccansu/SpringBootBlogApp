package com.example.folksdev.projectblog.dto.converter;

import com.example.folksdev.projectblog.dto.PostDto;
import com.example.folksdev.projectblog.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDtoConverter {

    private final PostUserDtoConverter postUserDtoConverter;
    private final PostCommentDtoConverter postCommentDtoConverter;

    public PostDtoConverter(PostUserDtoConverter postUserDtoConverter, PostCommentDtoConverter postCommentDtoConverter) {
        this.postUserDtoConverter = postUserDtoConverter;
        this.postCommentDtoConverter = postCommentDtoConverter;
    }

    public PostDto convertToPostDto(Post from) {
        return new PostDto(
                from.getId(),
                from.getTitle(),
                from.getBody(),
                from.getCreationDate(),
                from.getComments()
                        .stream()
                        .map(postCommentDtoConverter::convert)
                        .collect(Collectors.toList()),
                postUserDtoConverter.convert(from.getUser())

        );
    }

    public List<PostDto> convertToPostDtoList(List<Post> posts) {
        return posts
                .stream()
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
    }
}