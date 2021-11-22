package com.example.folksdev.projectblog.dto

import com.example.folksdev.projectblog.model.Comment
import com.example.folksdev.projectblog.model.Post
import com.example.folksdev.projectblog.model.User
import java.time.LocalDate
import java.time.LocalDateTime

data class CommentDto  @JvmOverloads constructor(
        val id:String,
        val body:String,
        val creationDate: LocalDate,
        val user: CommentUserDto,
        val posts:CommentPostDto,


        )
