package com.example.folksdev.projectblog.configuration;

import com.example.folksdev.projectblog.model.Comment;
import com.example.folksdev.projectblog.model.Gender;
import com.example.folksdev.projectblog.model.Post;
import com.example.folksdev.projectblog.model.User;
import com.example.folksdev.projectblog.repository.CommentRepository;
import com.example.folksdev.projectblog.repository.PostRepository;
import com.example.folksdev.projectblog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;


@Component
@ConditionalOnProperty(name = "command.line.runner.enable", havingValue = "true")
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public DataLoader(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User("cansu",
                "genc",
                "gnccansu",
                LocalDate.now(),
                "cansu123@gmail.com",
                Gender.FEMALE,
                Collections.emptySet(),
                Collections.emptySet()
        );

        Post post = new Post("hi!",
                "hi folksie!",
                LocalDate.now(),
                Collections.emptyList(),
                user);

        postRepository.save(post);

        User user2 = new User("gencay",
                "genc",
                "gncgncy",
                LocalDate.now(),
                "gencay123@gmail.com",
                Gender.MALE,
                Collections.emptySet(),
                Collections.emptySet()

        );

        Post post2 = new Post("hello!",
                "hello folksie!",
                LocalDate.now(),
                Collections.emptyList(),
                user2);

        Comment comment = new Comment(
                "hello spring boot",
                LocalDate.now(),
                post2,
                user2

        );

        commentRepository.save(comment);

    }
}

