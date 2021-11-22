package com.example.folksdev.projectblog.dto.ruquest

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class CreateCommentRequest(


        @field:NotBlank
        val userId: String,
        @field:NotBlank
        val postId: String,
        val body:String,


)
