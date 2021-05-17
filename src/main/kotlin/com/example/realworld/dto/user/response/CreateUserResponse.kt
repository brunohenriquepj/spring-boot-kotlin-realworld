package com.example.realworld.dto.user.response

data class CreateUserResponse(val user: CreateUserResponseData)

data class CreateUserResponseData(
    val userName: String,
    val email: String,
    val token: String,
    val bio: String?,
    val image: String?
)
