package com.example.realworld.adapter

import com.example.realworld.adapter.implementation.UserAdapterImpl
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.util.builder.user.UserBuilder
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import org.junit.jupiter.api.Test

class UserAdapterTest {
    private val adapter: UserAdapter = UserAdapterImpl()

    @Test
    fun `toCreateUserResponseData should return CreateUserResponseData`() {
        // arrange
        val user = UserBuilder().build()

        val expected = CreateUserResponseData(user.userName, user.email, "TODO: add jwt token", user.bio, user.image)

        // act
        val actual = adapter.toCreateUserResponseData(user)

        // assert
        actual shouldBeEqualToComparingFields expected

    }
}