package com.example.realworld.util.builder.user

import com.example.realworld.util.FakerPtBr
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class UsernamePasswordAuthenticationTokenBuilder {
    private val instance: UsernamePasswordAuthenticationToken

    init {
        val faker = FakerPtBr.generate()
        instance = UsernamePasswordAuthenticationToken(
            faker.internet().emailAddress(),
            faker.internet().password()
        )
    }

    fun build(): UsernamePasswordAuthenticationToken {
        return instance
    }
}