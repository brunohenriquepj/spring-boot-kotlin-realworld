package com.example.realworld.handler

import com.example.realworld.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BadCredentialsExceptionHandler {

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException::class)
    fun handle(exception: BadCredentialsException): ErrorResponse {
        return ErrorResponse.of("Username or password invalid!")
    }
}
