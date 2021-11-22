package com.example.folksdev.projectblog.dto

import com.example.folksdev.projectblog.model.Comment
import com.example.folksdev.projectblog.model.Gender
import com.example.folksdev.projectblog.model.Post
import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate
import java.time.LocalDateTime

data class UserDto  @JvmOverloads constructor(
        val id:String,
        val name:String,
        val lastName:String,
        val displayName: String,
        val creationDate: LocalDate,
        val email:String,
        val gender: Gender,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val posts:List<UserPostDto>? = ArrayList(),
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val comments: List<UserCommentDto>? = ArrayList(),
)
