package com.example.realworld.util.extension

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import java.net.URI

inline fun <reified T> TestRestTemplate.getForEntity(uri: String, token: String?): ResponseEntity<T> {
    return this.requestForEntity(uri = uri, httpMethod = HttpMethod.GET, token = token)
}

inline fun <reified T> TestRestTemplate.putForEntity(uri: String, body: Any, token: String?): ResponseEntity<T> {
    return this.requestForEntity(uri = uri, httpMethod = HttpMethod.PUT, body = body, token = token)
}

inline fun <reified T> TestRestTemplate.requestForEntity(
    uri: String,
    httpMethod: HttpMethod,
    body: Any? = null,
    token: String?
): ResponseEntity<T> {
    val headers = LinkedMultiValueMap<String, String>()
    headers[HttpHeaders.AUTHORIZATION] = "Bearer $token"

    val requestEntity = RequestEntity(body, headers, httpMethod, URI.create(uri))
    return this.exchange(requestEntity, T::class.java)
}
