package com.example.realworld.unit.adapter

import com.example.realworld.adapter.UserAdapter
import com.example.realworld.adapter.implementation.UserAdapterImpl
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.dto.user.response.LoginResponseData
import com.example.realworld.dto.user.response.UpdateUserResponseData
import com.example.realworld.util.builder.common.StringBuilder
import com.example.realworld.util.builder.user.UserBuilder
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import org.junit.jupiter.api.Test

class UserAdapterTest {
    private val adapter: UserAdapter = UserAdapterImpl()

    @Test
    fun `toCreateUserResponseData should return CreateUserResponseData`() {
        // arrange
        val user = UserBuilder().build()
        val authenticationToken = StringBuilder().build()

        val expected = CreateUserResponseData(
            userName = user.userName,
            email = user.email,
            token = authenticationToken,
            bio = user.bio,
            image = user.image
        )

        // act
        val actual = adapter.toCreateUserResponseData(user, authenticationToken)

        // assert
        actual shouldBeEqualToComparingFields expected

    }

    @Test
    fun `toLoginResponseData should return LoginResponseData`() {
        // arrange
        val user = UserBuilder().build()
        val token = StringBuilder().build()

        val expected = LoginResponseData(
            userName = user.userName,
            email = user.email,
            token = token,
            bio = user.bio,
            image = user.image
        )

        // act
        val actual = adapter.toLoginResponseData(user, token)

        // assert
        actual shouldBeEqualToComparingFields expected
    }

    @Test
    fun `toUpdateUserResponseData should return UpdateUserResponseData`() {
        // arrange
        val user = UserBuilder().build()
        val token = StringBuilder().build()

        val expected = UpdateUserResponseData(
            userName = user.userName,
            email = user.email,
            bio = user.bio,
            image = user.image,
            token = token
        )

        // act
        val actual = adapter.toUpdateUserResponseData(user, token)

        // assert
        actual shouldBeEqualToComparingFields expected
    }
}
