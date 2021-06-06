package com.example.realworld.handler

import com.example.realworld.dto.ErrorResponse
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RequestValidationErrorHandler(private val messageSource: MessageSource) {

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(exception: MethodArgumentNotValidException): ErrorResponse {
        val errors = exception.bindingResult.fieldErrors
            .map(this::getError)
            .toTypedArray()

        return ErrorResponse.of(errors)
    }

    fun getError(fieldError: FieldError): String {
        val message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())
        return "${fieldError.field}: $message"
    }
}

