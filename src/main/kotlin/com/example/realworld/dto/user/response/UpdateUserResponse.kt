package com.example.realworld.dto.user.response

data class UpdateUserResponse(val user: UpdateUserResponseData? = null)

data class UpdateUserResponseData(
    val userName: String = "",
    val email: String = "",
    val bio: String? = null,
    val image: String? = null,
    val token: String = ""
)
