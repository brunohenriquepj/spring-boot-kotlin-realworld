package com.example.realworld.adapter

import com.example.realworld.dto.user.request.CreateUserRequest
import com.example.realworld.dto.user.response.CreateUserResponse

interface CreateUserRequestAdapter {
    fun toCreateUserResponse(request: CreateUserRequest): CreateUserResponse
}