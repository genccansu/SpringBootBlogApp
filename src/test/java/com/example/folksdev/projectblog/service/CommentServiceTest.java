package com.example.folksdev.projectblog.service;

import com.example.folksdev.projectblog.TestSupport;
import com.example.folksdev.projectblog.dto.CommentDto;
import com.example.folksdev.projectblog.dto.converter.CommentDtoConverter;
import com.example.folksdev.projectblog.dto.ruquest.CreateCommentRequest;
import com.example.folksdev.projectblog.dto.ruquest.UpdateCommentRequest;
import com.example.folksdev.projectblog.exception.CommentNotFoundException;
import com.example.folksdev.projectblog.model.Comment;
import com.example.folksdev.projectblog.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest extends TestSupport {
    private CommentRepository commentRepository;
    private CommentDtoConverter commentDtoConverter;
    private PostService postService;
    private UserService userService;

    private CommentService commentService;


    @BeforeEach
    void setUp() {
        commentRepository = Mockito.mock(CommentRepository.class);
        commentDtoConverter = Mockito.mock(CommentDtoConverter.class);
        postService = Mockito.mock(PostService.class);
        userService = Mockito.mock(UserService.class);

        commentService= new CommentService(commentRepository,commentDtoConverter,postService,userService);
    }


    @Test
    void testCreateComment_whenCreateCommentRequest_shouldReturnCreateCommentDto() {

        CreateCommentRequest createCommentRequest = generateCreateCommentRequest();
        Comment comment = generateComment();
        CommentDto exceptedCommentDto = generateCommentDto();

        Mockito.when(userService.findByUserId(createCommentRequest.getUserId())).thenReturn(generateUser());
        Mockito.when(postService.fındByPostId(createCommentRequest.getPostId())).thenReturn(generatePost());
        Mockito.when(commentRepository.save(comment)).thenReturn(comment);
        Mockito.when(commentDtoConverter.convertToCommentDto(comment)).thenReturn(exceptedCommentDto);


        CommentDto result = commentService.createComment(createCommentRequest);

        assertEquals(exceptedCommentDto, result);

        Mockito.verify(userService).findByUserId("UserId");
        Mockito.verify(postService).fındByPostId("PostId");
        Mockito.verify(commentDtoConverter).convertToCommentDto(comment);
        Mockito.verify(commentRepository).save(comment);
    }

    @Test
    void testGetCommentById_whenIdExist_shouldReturnCommentDto(){
        Comment comment = generateComment();
        CommentDto exceptedCommentDto = generateCommentDto();

        Mockito.when(commentRepository.findById("id")).thenReturn(Optional.of(comment));
        Mockito.when(commentDtoConverter.convertToCommentDto(comment)).thenReturn(exceptedCommentDto);

        CommentDto result = commentService.getCommentById("id");

        assertEquals(exceptedCommentDto,result);

        Mockito.verify(commentRepository).findById("id");
        Mockito.verify(commentDtoConverter).convertToCommentDto(comment);
        Mockito.verifyNoInteractions(postService);
        Mockito.verifyNoInteractions(userService);
    }
    @Test
    void testGetCommentById_whenIdNotExist_shouldReturnCommentNotFoundException(){

        Mockito.when(commentRepository.findById("id")).thenThrow(CommentNotFoundException.class);

        assertThrows(CommentNotFoundException.class,
                ()-> commentService.getCommentById("id"));

        Mockito.verify(commentRepository).findById("id");
        Mockito.verifyNoInteractions(commentDtoConverter);
        Mockito.verifyNoInteractions(postService);
        Mockito.verifyNoInteractions(userService);
    }

    @Test
    void testGetAllCommentList_shouldReturnListCommentDto(){

        List<Comment> commentList = generateCommentList();
        List<CommentDto> exceptedCommentDtoList = generateListCommentDto();

        Mockito.when(commentRepository.findAll()).thenReturn(commentList);
        Mockito.when(commentDtoConverter.convertToCommentDtoList(commentList)).thenReturn(exceptedCommentDtoList);

        List<CommentDto> result = commentService.getAllCommentDtoList();

        assertEquals(exceptedCommentDtoList,result);

        Mockito.verify(commentRepository).findAll();
        Mockito.verify(commentDtoConverter).convertToCommentDtoList(commentList);
        Mockito.verifyNoInteractions(postService);
        Mockito.verifyNoInteractions(userService);

    }
    @Test
    void testGetAllCommentList_shouldThrowCommentNotFoundException(){

        Mockito.when(commentRepository.findAll()).thenThrow(CommentNotFoundException.class);

        assertThrows(CommentNotFoundException.class,
                ()->commentService.getAllCommentDtoList());

        Mockito.verify(commentRepository).findAll();
        Mockito.verifyNoInteractions(userService);
        Mockito.verifyNoInteractions(postService);
        Mockito.verifyNoInteractions(commentDtoConverter);
    }

    @Test
    void testUpdateComment_whenIdExist_shouldReturnCommentDto(){
        UpdateCommentRequest updateCommentRequest = generateUpdateCommentRequest();
        Comment updateComment = generateUpdateComment(generateComment(),generateUpdateCommentRequest());
        CommentDto exceptedCommentDto = generateCommentDto();

        Mockito.when(commentRepository.findById("id")).thenReturn(Optional.of(generateComment()));
        Mockito.when(commentRepository.save(updateComment)).thenReturn(updateComment);
        Mockito.when(commentDtoConverter.convertToCommentDto(updateComment)).thenReturn(exceptedCommentDto);

        CommentDto result = commentService.updateCommentById("id",updateCommentRequest);

        assertEquals(exceptedCommentDto,result);

        Mockito.verify(commentRepository).findById("id");
        Mockito.verify(commentRepository).save(updateComment);
        Mockito.verify(commentDtoConverter).convertToCommentDto(updateComment);
        Mockito.verifyNoInteractions(userService);
        Mockito.verifyNoInteractions(postService);
    }
    @Test
    void testUpdateComment_whenIdNotExist_shouldThrowCommentNotFoundException(){

        UpdateCommentRequest updateCommentRequest = generateUpdateCommentRequest();

        Mockito.when(commentRepository.findById("id")).thenThrow(CommentNotFoundException.class);

        assertThrows(CommentNotFoundException.class,
                ()->commentService.updateCommentById("id",updateCommentRequest));

        Mockito.verify(commentRepository).findById("id");
        Mockito.verifyNoInteractions(userService);
        Mockito.verifyNoInteractions(postService);
        Mockito.verifyNoInteractions(commentDtoConverter);

    }

    @Test
    void testDeleteCommentById_whenIdExist_shouldReturnString(){

        Comment comment = generateComment();
        CommentDto exceptedCommentDto = generateCommentDto();

        Mockito.when(commentRepository.findById("id")).thenReturn(Optional.of(comment));
        Mockito.when(commentDtoConverter.convertToCommentDto(comment)).thenReturn(exceptedCommentDto);

        String result = commentService.deleteCommentById("id");

        assertEquals("id comment deleted.",result);

        Mockito.verify(commentRepository).findById("id");
        Mockito.verify(commentDtoConverter).convertToCommentDto(comment);
        Mockito.verifyNoInteractions(userService);
        Mockito.verifyNoInteractions(postService);

    }

    @Test
    void testDeleteCommentById_whenIdNotExist_shouldThrowCommentNotFoundException(){
        Mockito.when(commentRepository.findById("id")).thenThrow(CommentNotFoundException.class);

        assertThrows(CommentNotFoundException.class,
                ()->commentService.deleteCommentById("id"));

        Mockito.verify(commentRepository).findById("id");
        Mockito.verifyNoInteractions(userService);
        Mockito.verifyNoInteractions(postService);
        Mockito.verifyNoInteractions(commentDtoConverter);

    }
}