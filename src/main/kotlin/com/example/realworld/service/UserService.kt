package com.example.realworld.service

import com.example.realworld.dto.user.request.CreateUserRequestData
import com.example.realworld.dto.user.request.LoginRequestData
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.dto.user.response.GetCurrentUserResponseData
import com.example.realworld.dto.user.response.LoginResponseData

interface UserService {
    fun create(request: CreateUserRequestData): CreateUserResponseData
    fun login(request: LoginRequestData): LoginResponseData
    fun getCurrentUser(): GetCurrentUserResponseData
}