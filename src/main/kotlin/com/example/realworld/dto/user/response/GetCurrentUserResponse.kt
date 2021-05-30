package com.example.realworld.dto.user.response

data class GetCurrentUserResponse(val user: GetCurrentUserResponseData =  GetCurrentUserResponseData())

data class GetCurrentUserResponseData(
    val userName: String = "",
    val email: String = "",
    val bio: String? = null,
    val image: String? = null
)
