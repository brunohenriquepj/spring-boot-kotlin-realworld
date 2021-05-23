package com.example.realworld.adapter.implementation

import com.example.realworld.adapter.UserAdapter
import com.example.realworld.annotation.RequestScopeComponent
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.entity.User

@RequestScopeComponent
class UserAdapterImpl : UserAdapter {
    override fun toCreateUserResponseData(user: User, authenticationToken: String): CreateUserResponseData {
        return CreateUserResponseData(
            userName = user.userName,
            email = user.email,
            token = authenticationToken,
            bio = user.bio,
            image = user.image
        )
    }
}