package com.example.realworld.unit.adapter

import com.example.realworld.adapter.implementation.UpdateUserRequestDataAdapterImpl
import com.example.realworld.entity.User
import com.example.realworld.util.builder.common.StringBuilder
import com.example.realworld.util.builder.user.UpdateUserRequestDataBuilder
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.password.PasswordEncoder

class UpdateUserRequestDataAdapterTest {
    private val passwordEncoderMock: PasswordEncoder = mockk()
    private val adapter = UpdateUserRequestDataAdapterImpl(passwordEncoderMock)

    @Test
    fun `toUser should return User`() {
        // arrange
        val request = UpdateUserRequestDataBuilder().build()

        val passwordEncoded = StringBuilder().build()
        every { passwordEncoderMock.encode(request.password) } returns passwordEncoded

        val expected = User(
            email = request.email,
            userName = request.userName,
            password = passwordEncoded,
            bio = request.bio,
            image = request.image
        )

        // act
        val actual = adapter.toUser(request)

        // assert
        verify(exactly = 1) {
            passwordEncoderMock.encode(request.password)
        }

        actual shouldBeEqualToComparingFields expected
    }
}
