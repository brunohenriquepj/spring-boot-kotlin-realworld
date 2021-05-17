package com.example.realworld.adapter.implementation

import com.example.realworld.adapter.UserAdapter
import com.example.realworld.annotation.RequestScopeComponent
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.entity.User

@RequestScopeComponent
class UserAdapterImpl : UserAdapter {
    override fun toCreateUserResponseData(user: User): CreateUserResponseData {
        return CreateUserResponseData(
            user.userName,
            user.email,
            "TODO: add jwt token" /* TODO: add jwt token */,
            user.bio,
            user.image
        )
    }
}