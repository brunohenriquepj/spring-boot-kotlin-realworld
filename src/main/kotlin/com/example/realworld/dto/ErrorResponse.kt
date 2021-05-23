package com.example.realworld.dto

data class ErrorResponse(val errors: ErrorResponseData) {
    companion object {
        fun of(body: Array<String>): ErrorResponse {
            return ErrorResponse(ErrorResponseData(body))
        }
    }
}

class ErrorResponseData(val body: Array<String>)