package com.example.folksdev.projectblog.dto.ruquest

import javax.validation.constraints.NotBlank

data class UpdateCommentRequest(
        @field:NotBlank
        val body: String,
)
