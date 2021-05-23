package com.example.realworld.service

import com.example.realworld.dto.user.request.CreateUserRequestData
import com.example.realworld.dto.user.response.CreateUserResponseData

interface UserService {
    fun create(createUserRequestData: CreateUserRequestData): CreateUserResponseData
}