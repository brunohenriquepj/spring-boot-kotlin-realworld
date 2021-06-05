package com.example.realworld.unit.handler

import com.example.realworld.dto.ErrorResponse
import com.example.realworld.handler.ExceptionHandler
import com.example.realworld.util.builder.common.StringBuilder
import io.kotest.matchers.collections.shouldContainAll
import org.junit.jupiter.api.Test

class ExceptionHandlerTest {
    private val handler = ExceptionHandler()

    @Test
    fun `handle should return default message`() {
        // arrange
        val message = StringBuilder().build()
        val exception = Exception(message)

        val expected = ErrorResponse.of("An internal error occurred!")

        // act
        val actual = handler.handle(exception)

        // assert
        actual.errors.body.toList() shouldContainAll expected.errors.body.toList()
    }
}