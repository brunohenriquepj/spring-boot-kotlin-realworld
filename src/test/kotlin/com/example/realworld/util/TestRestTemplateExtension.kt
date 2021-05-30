package com.example.realworld.util

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import java.net.URI

inline fun <reified T> TestRestTemplate.getForEntity(uri: String, token: String?): ResponseEntity<T> {
    val headers = LinkedMultiValueMap<String, String>()
    headers[HttpHeaders.AUTHORIZATION] = "Bearer $token"

    val requestEntity = RequestEntity<Any>(headers, HttpMethod.GET, URI.create(uri))
    return this.exchange(requestEntity, T::class.java)
}
