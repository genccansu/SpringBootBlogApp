package com.example.folksdev.projectblog.dto.ruquest

import com.example.folksdev.projectblog.model.Gender
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class CreateUserRequest(
        @field:NotBlank
        val name:String,
        @field:NotBlank
        val lastname:String,
        @field:NotBlank
        val  displayName:String,
        @field:Email
        val email:String,
        @field:Enumerated(EnumType.STRING)
        val gender: Gender,

        )
