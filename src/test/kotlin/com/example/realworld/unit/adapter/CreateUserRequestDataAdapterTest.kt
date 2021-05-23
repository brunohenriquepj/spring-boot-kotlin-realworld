package com.example.realworld.unit.adapter

import com.example.realworld.adapter.CreateUserRequestDataAdapter
import com.example.realworld.adapter.implementation.CreateUserRequestDataAdapterImpl
import com.example.realworld.entity.User
import com.example.realworld.util.builder.common.StringBuilder
import com.example.realworld.util.builder.user.CreateUserRequestDataBuilder
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.password.PasswordEncoder

class CreateUserRequestDataAdapterTest {
    private var passwordEncoderMock: PasswordEncoder = mockk()
    private var adapter: CreateUserRequestDataAdapter = CreateUserRequestDataAdapterImpl(passwordEncoderMock)

    @Test
    fun `toUser should return user`() {
        // arrange
        val requestData = CreateUserRequestDataBuilder().build()
        val passwordEncoded = StringBuilder().build()
        val expected = User(
            email = requestData.email,
            userName = requestData.userName,
            password = passwordEncoded,
            bio = null,
            image = null
        )

        every { passwordEncoderMock.encode(requestData.password) } returns passwordEncoded

        // act
        val actual = adapter.toUser(requestData)
        println(adapter)

        // assert
        verify(exactly = 1) {
            passwordEncoderMock.encode(requestData.password)
        }

        actual shouldBeEqualToComparingFields expected
    }
}