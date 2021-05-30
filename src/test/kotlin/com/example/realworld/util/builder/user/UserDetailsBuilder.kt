package com.example.realworld.util.builder.user

import com.example.realworld.entity.UserDetailsImpl
import com.example.realworld.util.builder.user.UserBuilder
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsBuilder {
    val instance : UserDetails

    init {
        instance = UserDetailsImpl(user = UserBuilder().build())
    }

    fun build(): UserDetails {
        return instance
    }
}