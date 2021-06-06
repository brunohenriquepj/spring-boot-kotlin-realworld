package com.example.realworld.unit.adapter

import com.example.realworld.adapter.implementation.UpdateUserRequestDataAdapterImpl
import com.example.realworld.entity.User
import com.example.realworld.util.builder.user.UpdateUserRequestDataBuilder
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.mockk
import org.junit.jupiter.api.Test

class UpdateUserRequestDataAdapterTest {
    private val adapter = UpdateUserRequestDataAdapterImpl(mockk())

    @Test
    fun `toUser should return User`() {
        // arrange
        val request = UpdateUserRequestDataBuilder().build()

        val expected = User(
            email = request.email,
            userName = request.userName,
            password = request.password,
            bio = request.bio,
            image = request.image
        )

        // act
        val actual = adapter.toUser(request)

        // assert
        actual shouldBeEqualToComparingFields expected
    }
}
