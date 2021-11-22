package com.example.folksdev.projectblog;

import com.example.folksdev.projectblog.dto.*;
import com.example.folksdev.projectblog.dto.ruquest.*;
import com.example.folksdev.projectblog.model.Comment;
import com.example.folksdev.projectblog.model.Gender;
import com.example.folksdev.projectblog.model.Post;
import com.example.folksdev.projectblog.model.User;



import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class TestSupport {

    public User generateUser(){
        return  new User(
                "name",
                "lastName",
                "displayName",
                LocalDate.now(),
                "email",
                Gender.FEMALE,
                Collections.emptySet(),
                Collections.emptySet()

        );
    }
    public UserDto generateUserDto(){
        return new UserDto("",
                "name",
                "lastName",
                "displayName",
                LocalDate.now(),
                "email",
                Gender.FEMALE,
                Collections.emptyList(),
                Collections.emptyList());
    }

    public List<User> generateUserList(){;
        return List.of(generateUser());
    }

    public List<UserDto> generateListUserDto(){
        return List.of(generateUserDto());
    }

    public CreateUserRequest generateCreateUserRequest(){
        return new CreateUserRequest("name",
                "lastName",
                "displayName",
                "email",
                Gender.FEMALE);
    }

    public UpdateUserRequest generateUpdateUserRequest(){
        return new UpdateUserRequest("name",
                "email");
    }

    public User generateUpdatedUser(User from, UpdateUserRequest userRequest){
        return new User(from.getId(),
                from.getName(),
                from.getLastName(),
                from.getDisplayName(),
                from.getCreationDate(),
                from.getEmail(),
                from.getGender(),
                from.getPosts(),
                from.getComments());
    }

    //POST

    public Post generatePost(){
        return new Post("id",
                "title",
                "body",
                LocalDate.now(),
                Collections.emptyList(),
                generateUser());
    }

    public Post generatePostUpdateFields(String title, String body){
        return new Post(title,
                body,
                LocalDate.now(),
                Collections.emptyList(),
                generateUser());
    }

    public PostDto generatePostDto(){

        return new PostDto("id",
                "title",
                "body",
                LocalDate.now(),
                Collections.emptyList(),
                generatePostUserDto());
    }

    public PostUserDto generatePostUserDto(){
        return  new PostUserDto("id",
                "name",
                "lastName",
                "displayName",
                "email");
    }

    public List<Post> generatePostList(){
        return List.of(generatePost());
    }

    public List<PostDto> generateListPostDto(){
        return List.of(generatePostDto());
    }

    public CreatePostRequest generateCreatePostRequest(){
        return new CreatePostRequest("title",
                "body");
    }

    public UpdatePostRequest generateUpdatePostRequest(){
        return new UpdatePostRequest("body",
                "title");
    }

    public Post generateUpdatePost(Post from, UpdatePostRequest request){
        return new Post(from.getId(),
                request.getTitle(), request.getBody(),
                from.getCreationDate(),from.getComments(), from.getUser());
    }

    //COMMENT

    public Comment generateComment(){
        return  new Comment("body",
                LocalDate.now(),
                generatePost(),
                generateUser());
    }
    public CommentDto generateCommentDto(){
        return new CommentDto("id",
                "body",
                LocalDate.now(),
                generateCommentUserDto(),
                generateCommentPostDto()
        );
    }
    public CommentUserDto generateCommentUserDto() {
        return new CommentUserDto("id",
                "name",
                "lastName",
                "displayName",
                Gender.MALE);
    }

    public CommentPostDto generateCommentPostDto() {
        return new CommentPostDto("id",
                "title",
                "body",
                LocalDate.now());

    }
    public List<Comment> generateCommentList(){
        return List.of(generateComment());
    }
    public List<CommentDto> generateListCommentDto(){
        return List.of(generateCommentDto());
    }

    public CreateCommentRequest generateCreateCommentRequest(){
        return  new CreateCommentRequest("UserId",
                "postId",
                "body");
    }
    public UpdateCommentRequest generateUpdateCommentRequest(){
        return new UpdateCommentRequest("body");
    }
    public Comment generateUpdateComment(Comment from,UpdateCommentRequest request){
        return new Comment(from.getId(),
                request.getBody(),
                from.getCreationDate(),
                from.getPosts(),
                from.getUser());
    }
}
