package com.example.realworld.adapter.implementation

import com.example.realworld.adapter.CreateUserRequestAdapter
import com.example.realworld.annotation.RequestScopeComponent
import com.example.realworld.dto.user.request.CreateUserRequest
import com.example.realworld.dto.user.response.CreateUserResponse
import com.example.realworld.dto.user.response.CreateUserResponseData

@RequestScopeComponent
class CreateUserRequestAdapterImpl : CreateUserRequestAdapter {
    override fun toCreateUserResponse(request: CreateUserRequest): CreateUserResponse {
        val responseData = CreateUserResponseData(
            request.user?.userName!!,
            request.user.email,
            "TODO: add jwt token" /* TODO: add jwt token*/,
            null,
            null
        )
        return CreateUserResponse(responseData)
    }
}