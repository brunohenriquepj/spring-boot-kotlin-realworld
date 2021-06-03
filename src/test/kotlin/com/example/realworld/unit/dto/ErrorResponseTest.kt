package com.example.realworld.unit.dto

import com.example.realworld.dto.ErrorResponse
import com.example.realworld.util.ListFactory
import com.example.realworld.util.builder.common.StringBuilder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ErrorResponseTest {
    @Test
    fun `of should set response data message`() {
        // arrange
        val message = StringBuilder().build()

        val expected = listOf(message)

        // act
        val actual = ErrorResponse.of(message)

        // assert
        actual.errors.body.toList() shouldBe expected
    }

    @Test
    fun `of should set response data messages`() {
        // arrange
        val messages = ListFactory.generate({ StringBuilder().build() })

        // act
        val actual = ErrorResponse.of(messages.toTypedArray())

        // assert
        actual.errors.body.toList() shouldBe messages
    }
}
