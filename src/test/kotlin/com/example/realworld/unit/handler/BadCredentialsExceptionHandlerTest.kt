package com.example.realworld.unit.handler

import com.example.realworld.dto.ErrorResponse
import com.example.realworld.handler.BadCredentialsExceptionHandler
import com.example.realworld.util.builder.common.StringBuilder
import io.kotest.matchers.collections.shouldContainAll
import org.junit.jupiter.api.Test
import org.springframework.security.authentication.BadCredentialsException

class BadCredentialsExceptionHandlerTest {
    private val handler: BadCredentialsExceptionHandler = BadCredentialsExceptionHandler()

    @Test
    fun `handle should return message`() {
        // arrange
        val message = StringBuilder().build()
        val exception = BadCredentialsException(message)

        val expected = ErrorResponse.of("Username or password invalid!")

        // act
        val actual = handler.handle(exception)

        // assert
        actual.errors.body.toList() shouldContainAll expected.errors.body.toList()
    }
}