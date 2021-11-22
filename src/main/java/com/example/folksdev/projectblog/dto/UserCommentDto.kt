package com.example.folksdev.projectblog.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class UserCommentDto(
        val id:String,
        val body:String,
        val creationDate: LocalDate,
)
