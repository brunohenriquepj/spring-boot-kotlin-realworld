package com.example.realworld.unit.handler

import com.example.realworld.dto.ErrorResponse
import com.example.realworld.dto.ErrorResponseData
import com.example.realworld.handler.RequestValidationErrorHandler
import com.example.realworld.util.ListFactory
import com.example.realworld.util.builder.FieldErrorBuilder
import com.example.realworld.util.builder.common.StringBuilder
import io.kotest.matchers.collections.shouldContainAll
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.web.bind.MethodArgumentNotValidException

class RequestValidationErrorHandlerTest {
    private val messageSourceMock: MessageSource = mockk()
    private val handler: RequestValidationErrorHandler = RequestValidationErrorHandler(messageSourceMock)

    @Test
    fun `handle should return message`() {
        // arrange
        val exception = mockk<MethodArgumentNotValidException>()

        val errors = ListFactory.generate({ FieldErrorBuilder().build() }, min = 1, max = 5)
        every { exception.bindingResult.fieldErrors } returns errors

        val messages = errors.map {
            val message = StringBuilder().build()
            every { messageSourceMock.getMessage(it, LocaleContextHolder.getLocale()) } returns message
            "${it.field}: $message"
        }.toTypedArray()

        val expected = ErrorResponse(ErrorResponseData(messages))

        // act
        val actual = handler.handle(exception)

        // assert
        verify(exactly = 1) {
            errors.map { messageSourceMock.getMessage(it, LocaleContextHolder.getLocale()) }
        }
        
        actual.errors.body.toList() shouldContainAll expected.errors.body.toList()
    }
}

