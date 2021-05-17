package com.example.realworld.dto.user.request

data class CreateUserRequest(val user: CreateUserRequestData)

data class CreateUserRequestData(val userName: String, val email: String, val password: String)
