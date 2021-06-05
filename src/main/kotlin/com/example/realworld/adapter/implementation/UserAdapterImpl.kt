package com.example.realworld.adapter.implementation

import com.example.realworld.adapter.UserAdapter
import com.example.realworld.annotation.RequestScopeComponent
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.dto.user.response.LoginResponseData
import com.example.realworld.entity.User
import org.springframework.stereotype.Component

// @RequestScopeComponent
@Component
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

    override fun toLoginResponseData(user: User, authenticationToken: String): LoginResponseData {
        return LoginResponseData(
            userName = user.userName,
            email = user.email,
            token = authenticationToken,
            bio = user.bio,
            image = user.image
        )
    }
}
