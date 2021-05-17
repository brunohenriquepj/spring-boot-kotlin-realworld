package com.example.realworld.handler

import com.example.realworld.constants.ProfileConstant
import com.example.realworld.dto.ErrorResponse
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Profile(ProfileConstant.Production)
class ExceptionHandler {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handle(exception: Exception): ErrorResponse {
        return ErrorResponse.of(arrayOf("An internal error occurred!"))
    }
}