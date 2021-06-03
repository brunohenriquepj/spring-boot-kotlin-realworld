package com.example.realworld.dto.user.response

data class GetCurrentUserResponse(val user: GetCurrentUserResponseData? = null)

data class GetCurrentUserResponseData(
    val userName: String = "",
    val email: String = "",
    val bio: String? = null,
    val image: String? = null
)
