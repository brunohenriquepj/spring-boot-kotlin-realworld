package com.example.realworld.handler

import com.example.realworld.dto.ErrorResponse
import com.example.realworld.dto.ErrorResponseData
import com.example.realworld.exception.BusinessValidationException
import com.example.realworld.util.builder.common.StringBuilder
import io.kotest.matchers.collections.shouldContainAll
import org.junit.jupiter.api.Test

class BusinessValidationExceptionHandlerTest {
    private val handler: BusinessValidationExceptionHandler = BusinessValidationExceptionHandler()

    @Test
    fun `handle should return message`() {
        // arrange
        val message = StringBuilder().build()
        val exception = BusinessValidationException(message)

        val expected = ErrorResponse(ErrorResponseData(arrayOf(message)))

        // act
        val actual = handler.handle(exception)

        // assert
        actual.errors.body.toList() shouldContainAll expected.errors.body.toList()
    }
}