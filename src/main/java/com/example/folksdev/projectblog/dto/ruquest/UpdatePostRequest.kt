package com.example.folksdev.projectblog.dto.ruquest

import javax.validation.constraints.NotBlank

data class UpdatePostRequest(
        @field:NotBlank
        val body: String,
        @field:NotBlank
        val title: String,
)
