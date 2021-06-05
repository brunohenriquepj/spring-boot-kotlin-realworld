package com.example.realworld.handler

import com.example.realworld.dto.ErrorResponse
import com.example.realworld.exception.BusinessValidationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BusinessValidationExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessValidationException::class)
    fun handle(exception: BusinessValidationException): ErrorResponse {
        return ErrorResponse.of(exception.message)
    }
}