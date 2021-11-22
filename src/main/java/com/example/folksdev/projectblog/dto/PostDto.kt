package com.example.folksdev.projectblog.dto

import com.example.folksdev.projectblog.model.Comment
import com.example.folksdev.projectblog.model.User
import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate
import java.time.LocalDateTime

data class PostDto  @JvmOverloads constructor(
        val id: String,
        val title: String,
        val body: String,
        val creationDate: LocalDate,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val comments: List<PostCommentDto>? = ArrayList(),
        val user:  PostUserDto,
)
