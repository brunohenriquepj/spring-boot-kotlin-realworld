package com.example.realworld.handler

import com.example.realworld.dto.ErrorResponse
import com.example.realworld.dto.ErrorResponseData
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

        val expected = ErrorResponse(ErrorResponseData(arrayOf("An internal error occurred!")))

        // act
        val actual = handler.handle(exception)

        // assert
        actual.errors.body.toList() shouldContainAll expected.errors.body.toList()
    }
}