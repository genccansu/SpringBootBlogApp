package com.example.folksdev.projectblog.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class CommentPostDto(
        val id: String,
        val title: String,
        val body: String,
        val creationDate: LocalDate,
)
