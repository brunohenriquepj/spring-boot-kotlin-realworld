package com.example.realworld.dto

data class ErrorResponse(val errors: ErrorResponseData = ErrorResponseData()) {
    companion object {
        fun of(messages: Array<String>): ErrorResponse {
            return ErrorResponse(ErrorResponseData(messages))
        }

        fun of(message: String): ErrorResponse {
            return ErrorResponse(ErrorResponseData(arrayOf(message)))
        }
    }
}

class ErrorResponseData(val body: Array<String> = arrayOf())