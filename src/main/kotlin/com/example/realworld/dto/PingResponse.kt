package com.example.realworld.dto

data class PingResponse(val ping: PingResponseData) {
    companion object {
        fun of(message: String): PingResponse {
            val responseData = PingResponseData(message)
            return PingResponse(responseData)
        }
    }
}

data class PingResponseData(val message: String)
