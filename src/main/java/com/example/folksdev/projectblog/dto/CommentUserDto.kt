package com.example.folksdev.projectblog.dto

import com.example.folksdev.projectblog.model.Gender

data class CommentUserDto(
        val id: String,
        val name:String,
        val lastName:String,
        val displayName: String,
        val gender: Gender,
)
