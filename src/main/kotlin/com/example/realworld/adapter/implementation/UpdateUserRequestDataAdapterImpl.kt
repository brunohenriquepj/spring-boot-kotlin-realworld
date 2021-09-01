package com.example.realworld.adapter.implementation

import com.example.realworld.adapter.UpdateUserRequestDataAdapter
import com.example.realworld.dto.user.request.UpdateUserRequestData
import com.example.realworld.entity.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UpdateUserRequestDataAdapterImpl(private val passwordEncoder: PasswordEncoder) : UpdateUserRequestDataAdapter {
    override fun toUser(request: UpdateUserRequestData): User {
        return User(
            email = request.email,
            userName = request.userName,
            password = passwordEncoder.encode(request.password),
            bio = request.bio,
            image = request.image
        )
    }
}
