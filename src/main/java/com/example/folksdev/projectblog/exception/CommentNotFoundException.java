package com.example.folksdev.projectblog.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(String s) {
        super(s);
    }
}
