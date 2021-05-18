package com.example.realworld.unit.adapter

import com.example.realworld.adapter.CreateUserRequestAdapter
import com.example.realworld.adapter.implementation.CreateUserRequestAdapterImpl
import com.example.realworld.dto.user.request.CreateUserRequest
import com.example.realworld.dto.user.response.CreateUserResponse
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.util.builder.user.CreateUserRequestDataBuilder
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import org.junit.jupiter.api.Test

class CreateUserRequestAdapterTest {
    private val adapter: CreateUserRequestAdapter = CreateUserRequestAdapterImpl()

    @Test
    fun `toCreateUserResponse should return CreateUserResponse`() {
        // arrange
        val requestData = CreateUserRequestDataBuilder().build()
        val request = CreateUserRequest(requestData)

        val expected = CreateUserResponse(
            CreateUserResponseData(
                requestData.userName,
                requestData.email,
                "TODO: add jwt token",
                null,
                null
            )
        )

        // act
        val actual = adapter.toCreateUserResponse(request)

        // assert
        actual shouldBeEqualToComparingFields expected
    }
}

