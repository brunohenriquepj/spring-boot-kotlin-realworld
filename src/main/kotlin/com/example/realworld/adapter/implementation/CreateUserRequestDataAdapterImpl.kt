package com.example.realworld.adapter.implementation

import com.example.realworld.adapter.CreateUserRequestDataAdapter
import com.example.realworld.annotation.RequestScopeComponent
import com.example.realworld.dto.user.request.CreateUserRequestData
import com.example.realworld.entity.User
import org.springframework.security.crypto.password.PasswordEncoder

@RequestScopeComponent
class CreateUserRequestDataAdapterImpl(private val passwordEncoder: PasswordEncoder) : CreateUserRequestDataAdapter {
    override fun toUser(createUserRequestData: CreateUserRequestData): User {
        return User(
            email = createUserRequestData.email,
            userName = createUserRequestData.userName,
            password = passwordEncoder.encode(createUserRequestData.password)
        )
    }
}