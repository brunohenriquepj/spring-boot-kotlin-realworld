package com.example.realworld.adapter.implementation

import com.example.realworld.adapter.CreateUserRequestDataAdapter
import com.example.realworld.annotation.RequestScopeComponent
import com.example.realworld.dto.user.request.CreateUserRequestData
import com.example.realworld.entity.User

@RequestScopeComponent
class CreateUserRequestDataAdapterImpl : CreateUserRequestDataAdapter {
    override fun toUser(createUserRequestData: CreateUserRequestData): User {
        return User(createUserRequestData.email, createUserRequestData.userName)
    }
}