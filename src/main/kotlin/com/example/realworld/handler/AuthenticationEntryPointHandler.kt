package com.example.realworld.handler

import com.example.realworld.dto.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationEntryPointHandler : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.FORBIDDEN.value()

        val responseBody = ObjectMapper().writeValueAsString(ErrorResponse.of("Access denied!"))
        response.writer.write(responseBody!!)
    }
}