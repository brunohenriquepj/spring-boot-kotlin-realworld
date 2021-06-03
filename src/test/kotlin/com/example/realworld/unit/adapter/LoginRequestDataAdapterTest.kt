package com.example.realworld.unit.adapter

import com.example.realworld.adapter.implementation.LoginRequestDataAdapterImpl
import com.example.realworld.util.builder.user.LoginRequestDataBuilder
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import org.junit.jupiter.api.Test
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class LoginRequestDataAdapterTest {
    private val adapter = LoginRequestDataAdapterImpl()

    @Test
    fun `toUsernamePasswordAuthenticationToken should return UsernamePasswordAuthenticationToken`() {
        // arrange
        val request = LoginRequestDataBuilder().build()

        val expected = UsernamePasswordAuthenticationToken(request.email, request.password)

        // act
        val actual = adapter.toUsernamePasswordAuthenticationToken(request)

        // assert
        actual shouldBeEqualToComparingFields expected
    }
}

