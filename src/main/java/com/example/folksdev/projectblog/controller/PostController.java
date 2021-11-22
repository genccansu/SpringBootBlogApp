package com.example.folksdev.projectblog.controller;

import com.example.folksdev.projectblog.dto.PostDto;
import com.example.folksdev.projectblog.dto.ruquest.CreatePostRequest;
import com.example.folksdev.projectblog.dto.ruquest.UpdatePostRequest;
import com.example.folksdev.projectblog.model.Post;
import com.example.folksdev.projectblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<PostDto> getPostById(@PathVariable String id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping
    ResponseEntity<List<Post>> getAllPost() {
        return ResponseEntity.ok(postService.getAllPostList());
    }

    @PostMapping(path = "/{id}")
    ResponseEntity<PostDto> createPost(@PathVariable String id,
                                       @Valid @RequestBody CreatePostRequest postRequest) {
        PostDto cretePost = postService.createPost(id, postRequest);
        return new ResponseEntity<PostDto>(cretePost, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<PostDto> updatePostById(@Valid @PathVariable String id,
                                           @RequestBody UpdatePostRequest postRequest) {
        PostDto updatedPost = postService.updatePost(id, postRequest);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id]")
    ResponseEntity<String> deletePostById(@PathVariable String id) {
        String deletePost = postService.deletePostById(id);
        return new ResponseEntity<String>(deletePost, HttpStatus.ACCEPTED);
    }
}
