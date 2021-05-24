package com.example.realworld.dto.user.response

class LoginResponseData(
    val userName: String = "",
    val email: String = "",
    val token: String = "",
    val bio: String? = null,
    val image: String? = null
)

class LoginResponse(val user: LoginResponseData = LoginResponseData())
