package com.example.realworld.util.extension

import org.springframework.http.HttpHeaders
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder

fun MockHttpServletRequestBuilder.authorizationHeader(token: String?): MockHttpServletRequestBuilder {
    return this.header(HttpHeaders.AUTHORIZATION, "Bearer $token")
}
