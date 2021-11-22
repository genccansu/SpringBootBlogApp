package com.example.folksdev.projectblog.dto.ruquest

import javax.validation.constraints.NotBlank

data class CreatePostRequest(
        @field:NotBlank
        val title: String,

        @field:NotBlank
        val body: String,



)
