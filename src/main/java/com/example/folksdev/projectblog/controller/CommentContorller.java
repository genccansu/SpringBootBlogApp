package com.example.folksdev.projectblog.controller;

import com.example.folksdev.projectblog.dto.CommentDto;
import com.example.folksdev.projectblog.dto.ruquest.CreateCommentRequest;
import com.example.folksdev.projectblog.dto.ruquest.UpdateCommentRequest;
import com.example.folksdev.projectblog.model.Comment;
import com.example.folksdev.projectblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/comment")
public class CommentContorller {

    private final CommentService commentService;

    public CommentContorller(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable String id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @GetMapping(path = "/findall")
    public ResponseEntity<List<CommentDto>> getAllComment() {
        return ResponseEntity.ok(commentService.getAllCommentDtoList());
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CreateCommentRequest commentRequest) {
        CommentDto createComment = commentService.createComment(commentRequest);
        return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable String id,
                                                        @Valid @RequestBody UpdateCommentRequest commentRequest) {
        CommentDto updatedComment = commentService.updateCommentById(id, commentRequest);
        return new ResponseEntity<CommentDto>(updatedComment, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable String id) {
        String deleteComment = commentService.deleteCommentById(id);
        return new ResponseEntity<String>(deleteComment, HttpStatus.ACCEPTED);
    }
}
