package com.example.folksdev.projectblog.service;

import com.example.folksdev.projectblog.dto.PostDto;
import com.example.folksdev.projectblog.dto.converter.PostDtoConverter;
import com.example.folksdev.projectblog.dto.ruquest.CreatePostRequest;
import com.example.folksdev.projectblog.dto.ruquest.UpdatePostRequest;
import com.example.folksdev.projectblog.exception.PostNotFoundException;
import com.example.folksdev.projectblog.model.Post;
import com.example.folksdev.projectblog.model.User;
import com.example.folksdev.projectblog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostDtoConverter postDtoConverter;

    private final UserService userService;


    public PostService(PostRepository postRepository,
                       PostDtoConverter postDtoConverter,
                       UserService userService) {
        this.postRepository = postRepository;
        this.postDtoConverter = postDtoConverter;
        this.userService = userService;

    }

    public List<Post> getAllPostList() {
        return postRepository.findAll();
    }

    public List<PostDto> getAllPostDtoList() {
        return postDtoConverter.convertToPostDtoList(getAllPostList());
    }

    protected Post fındByPostId(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found!!"));
    }

    public String deletePostById(String id) {
        getPostById(id);
        postRepository.deleteById(id);

        return id + " post deleted.";

    }

    public PostDto getPostById(String id) {
        return postDtoConverter.convertToPostDto(fındByPostId(id));
    }

    public PostDto createPost(String id, CreatePostRequest postRequest) {
        User user = userService.findByUserId(id);

        Post post = new Post(
                postRequest.getTitle(),
                postRequest.getBody(),
                LocalDate.now(),
                Collections.emptyList(),
                user

        );
        return postDtoConverter.convertToPostDto(postRepository.save(post));

    }

    public PostDto updatePost(String id, UpdatePostRequest postRequest) {
        Post post = fındByPostId(id);

        Post updatedPost = new Post(
                post.getId(),
                postRequest.getTitle(),
                postRequest.getBody(),
                post.getCreationDate(),
                post.getComments(),
                post.getUser()

        );
        return postDtoConverter.convertToPostDto(postRepository.save(updatedPost));
    }


}
