package com.example.folksdev.projectblog.service;

import com.example.folksdev.projectblog.dto.CommentDto;
import com.example.folksdev.projectblog.dto.converter.CommentDtoConverter;
import com.example.folksdev.projectblog.dto.ruquest.CreateCommentRequest;
import com.example.folksdev.projectblog.dto.ruquest.UpdateCommentRequest;
import com.example.folksdev.projectblog.exception.CommentNotFoundException;
import com.example.folksdev.projectblog.model.Comment;
import com.example.folksdev.projectblog.model.Post;
import com.example.folksdev.projectblog.model.User;
import com.example.folksdev.projectblog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;

    private final PostService postService;
    private final UserService userService;
    public CommentService(CommentRepository commentRepository,
                          CommentDtoConverter commentDtoConverter,
                          PostService postService,
                          UserService userService) {
        this.commentRepository = commentRepository;
        this.commentDtoConverter = commentDtoConverter;
        this.postService = postService;
        this.userService = userService;
    }

    public List<CommentDto> getAllCommentDtoList(){
        return commentDtoConverter.convertToCommentDtoList(getAllCommentList());
    }

    protected  List<Comment> getAllCommentList(){
        return commentRepository.findAll();
    }

    protected Comment findCommentById(String id){
        return commentRepository.findById(id)
                .orElseThrow(()-> new CommentNotFoundException("Comment not found!!"));
    }

    public CommentDto getCommentById(String id){
        return commentDtoConverter.convertToCommentDto(findCommentById(id));
    }

    public CommentDto createComment(CreateCommentRequest commentRequest){
        User user = userService.findByUserId(commentRequest.getPostId());
        Post post = postService.fındByPostId(commentRequest.getPostId());

        Comment comment = new Comment(
            commentRequest.getBody(),
                LocalDate.now(),
               post,
                user);
        return commentDtoConverter.convertToCommentDto(commentRepository.save(comment));

    }

    public CommentDto updateCommentById(String id, UpdateCommentRequest commentRequest){

        Comment comment = findCommentById(id);

        Comment updatedComment = new Comment(
                comment.getId(),
                comment.getBody(),
                comment.getCreationDate(),
                comment.getPosts(),
                comment.getUser()


        );
        return commentDtoConverter.convertToCommentDto(commentRepository.save(updatedComment));

    }

    public String deleteCommentById(String id){
        getCommentById(id);
        commentRepository.deleteById(id);

        return  id+ " comment deleted.";
    }

}



