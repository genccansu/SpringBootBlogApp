package com.example.folksdev.projectblog.dto.ruquest

import javax.validation.constraints.NotBlank

data class UpdateUserRequest(
        @field:NotBlank
        val displayName: String,

        @field:NotBlank
        val email:String,
)
