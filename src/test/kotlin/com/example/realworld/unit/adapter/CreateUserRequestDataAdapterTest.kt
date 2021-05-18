package com.example.realworld.unit.adapter

import com.example.realworld.adapter.CreateUserRequestDataAdapter
import com.example.realworld.adapter.implementation.CreateUserRequestDataAdapterImpl
import com.example.realworld.entity.User
import com.example.realworld.util.builder.user.CreateUserRequestDataBuilder
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import org.junit.jupiter.api.Test

class CreateUserRequestDataAdapterTest {
    private val adapter: CreateUserRequestDataAdapter = CreateUserRequestDataAdapterImpl()

    @Test
    fun `toUser should return user`() {
        // arrange
        val requestData = CreateUserRequestDataBuilder().build()
        val expected = User(requestData.email, requestData.userName, null, null)

        // act
        val actual = adapter.toUser(requestData)
        println(adapter)

        // assert
        actual shouldBeEqualToComparingFields expected
    }
}