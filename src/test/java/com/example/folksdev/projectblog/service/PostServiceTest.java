package com.example.folksdev.projectblog.service;

import com.example.folksdev.projectblog.TestSupport;
import com.example.folksdev.projectblog.dto.PostDto;
import com.example.folksdev.projectblog.dto.converter.PostDtoConverter;
import com.example.folksdev.projectblog.dto.ruquest.CreatePostRequest;
import com.example.folksdev.projectblog.dto.ruquest.UpdatePostRequest;
import com.example.folksdev.projectblog.exception.PostNotFoundException;
import com.example.folksdev.projectblog.exception.UserNotFoundException;
import com.example.folksdev.projectblog.model.Post;
import com.example.folksdev.projectblog.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest extends TestSupport {

    private  PostRepository postRepository;
    private  PostDtoConverter postDtoConverter;
    private  UserService userService;

    private  PostService postService;

    @BeforeEach
    void setUp() {

        postRepository= Mockito.mock(PostRepository.class);
        postDtoConverter = Mockito.mock(PostDtoConverter.class);
        userService= Mockito.mock(UserService.class);

        postService = new PostService(postRepository,postDtoConverter,userService);
    }

        @Test
    void testCreatePost_whenCreatePostRequest_shouldReturnCreatePostDto() {

            CreatePostRequest createPostRequest = generateCreatePostRequest();
            Post post = generatePostUpdateFields(createPostRequest.getTitle(), createPostRequest.getBody());
            PostDto exceptedPostDto = generatePostDto();

            Mockito.when(userService.findByUserId("id")).thenReturn(generateUser());
            Mockito.when(postDtoConverter.convertToPostDto(post)).thenReturn(exceptedPostDto);
            Mockito.when(postRepository.save(post)).thenReturn(post);

            PostDto result = postService.createPost("id", createPostRequest);

            assertEquals(exceptedPostDto, result);

            Mockito.verify(userService).findByUserId("id");
            Mockito.verify(postDtoConverter).convertToPostDto(post);
            Mockito.verify(postRepository).save(post);
        }
        @Test
        void testCreatePost_whenUserIdNotExist_shouldThrowUserNotFoundException(){

        CreatePostRequest createPostRequest = generateCreatePostRequest();

        Mockito.when(userService.findByUserId("id")).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class,
                ()->postService.createPost("id",createPostRequest));

        Mockito.verify(userService).findByUserId("id");
        Mockito.verifyNoInteractions(postRepository);
        Mockito.verifyNoInteractions(postDtoConverter);

        }

        @Test
    void testGetPostById_whenIdExist_shouldReturnPostDto(){
        Post post = generatePost();
        PostDto exceptedPostDto = generatePostDto();

        Mockito.when(postRepository.findById("id")).thenReturn(Optional.of(post));
        Mockito.when(postDtoConverter.convertToPostDto(post)).thenReturn(exceptedPostDto);

        PostDto result = postService.getPostById("id");

        assertEquals(exceptedPostDto,result);

       Mockito.verify(postRepository).findById("id");
       Mockito.verify(postDtoConverter).convertToPostDto(post);
       Mockito.verifyNoInteractions(userService);
        }

        @Test
    void testGetPostById_whenIdNotExist_shouldThrowPostNotFoundException(){

        Mockito.when(postRepository.findById("id")).thenThrow(PostNotFoundException.class);

        assertThrows(PostNotFoundException.class,
                ()->postService.getPostById("id"));

        Mockito.verify(postRepository).findById("id");
        Mockito.verifyNoInteractions(postDtoConverter);
        Mockito.verifyNoInteractions(userService);

        }

        @Test
    void testGetAllPostList_shouldReturnListPostDto(){

        List<Post> postList = generatePostList();
        List<PostDto>  exceptedPostDtoList = generateListPostDto();


        Mockito.when(postRepository.findAll()).thenReturn(postList);
        Mockito.when(postDtoConverter.convertToPostDtoList(postList)).thenReturn(exceptedPostDtoList);

        List<PostDto> result = postService.getAllPostDtoList();

       assertEquals(exceptedPostDtoList,result);

       Mockito.verify(postRepository).findAll();
       Mockito.verify(postDtoConverter).convertToPostDtoList(postList);
       Mockito.verifyNoInteractions(userService);

        }

        @Test
    void testDeletePost_whenPostIdExist_shouldReturnString(){
          Post post = generatePost();
          PostDto postDto = generatePostDto();

          Mockito.when(postRepository.findById("id")).thenReturn(Optional.of(post));
          Mockito.when(postDtoConverter.convertToPostDto(post)).thenReturn(postDto);

          String result = postService.deletePostById("id");

          assertEquals("id post deleted.",result);

          Mockito.verify(postRepository).findById("id");
          Mockito.verify(postDtoConverter).convertToPostDto(post);
          Mockito.verifyNoInteractions(userService);
        }

        @Test
    void testDeletePost_whenPostIdNotExist_shouldThrowPostNotFoundException(){
        Mockito.when(postRepository.findById("id")).thenThrow(PostNotFoundException.class);

        assertThrows(PostNotFoundException.class,
                ()->postService.deletePostById("id"));

        Mockito.verify(postRepository).findById("id");
        Mockito.verifyNoInteractions(userService);
        Mockito.verifyNoInteractions(postDtoConverter);

        }

        @Test
    void testUpdatePost_whenPostIdExist_shouldReturnPostDto(){
            UpdatePostRequest updatePostRequest = generateUpdatePostRequest();
            Post updatedPost = generateUpdatePost(generatePost(),updatePostRequest);
            PostDto exceptedPostDto = generatePostDto();

            Mockito.when(postRepository.findById("id")).thenReturn(Optional.of(generatePost()));
            Mockito.when(postRepository.save(updatedPost)).thenReturn(updatedPost);
            Mockito.when(postDtoConverter.convertToPostDto(updatedPost)).thenReturn(exceptedPostDto);

            PostDto result = postService.updatePost("id",updatePostRequest);

            assertEquals(exceptedPostDto,result);

            Mockito.verify(postRepository).findById("id");
            Mockito.verify(postRepository).save(updatedPost);
            Mockito.verify(postDtoConverter).convertToPostDto(updatedPost);
            Mockito.verifyNoInteractions(userService);

        }
        @Test
    void testUpdatePost_whenPostIdNotExist_shouldThrowPostNotFoundException(){

        UpdatePostRequest updatePostRequest = generateUpdatePostRequest();

        Mockito.when(postRepository.findById("id")).thenThrow(PostNotFoundException.class);

        assertThrows(PostNotFoundException.class,
                ()-> postService.updatePost("id",updatePostRequest));

        Mockito.verify(postRepository).findById("id");
        Mockito.verifyNoInteractions(userService);
        Mockito.verifyNoInteractions(postDtoConverter);

        }
}