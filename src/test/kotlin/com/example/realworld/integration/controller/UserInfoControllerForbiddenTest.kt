package com.example.realworld.integration.controller

import com.example.realworld.dto.ErrorResponse
import com.example.realworld.repository.UserRepository
import com.example.realworld.service.UserService
import com.example.realworld.util.annotation.WebMvcIntegrationTest
import com.example.realworld.util.extension.authorizationHeader
import com.example.realworld.util.extension.shouldBeEqualJson
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@WebMvcIntegrationTest(controllers = [UserInfoControllerTest::class])
class UserInfoControllerForbiddenTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var userRepository: UserRepository

    @MockBean
    lateinit var userService: UserService

    @ParameterizedTest
    @ValueSource(
        strings = [
            "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJSZWFsV29ybGQgQXBpIiwic3ViIjoiaGVucnkub3Jkb25oZXNAZ21haWwuY29tIiwiaWF0IjoxNjIyMzY0MDY0LCJleHAiOjE2MjIzNjQxMjR9.03ELoPPeCy6hS9KPW1_bNEc3wIHWdMPNi8Mrt0jEGU_0x677s16AYCzmdzAl-qEliDxFeI4KMwtfM0KRlvZk1Q",
            "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJSZWFsV29ybGQgQXBpIiwic3ViIjoibWFyaWFoLnZpZWlyYUBsaXZlLmNvbSIsImlhdCI6MTYyMjM2NDE0MywiZXhwIjoxNjIyMzY0MjAzfQ.HHKkchLViZiBdpM9RT3etShKeAFOS_AvuOGRYTsHcMduinUQIYxODq7pCx4dvb_EZytNMZSQ3CSPvDrwyiJtBg",
            "",
            "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJSZWFsV29ybGQgQXBpIiwic3ViIjoibWFyaWFoLnZpZWlyYUBsaXZlLmNvbSIsImlhdCI6MTYyMjM2NDE0MywiZXhwIjoxNjIyMzY0MjAzfQ"
        ]
    )
    fun `getCurrentUser should return forbidden error when token is invalid`(token: String) {
        // arrange
        val expected = ErrorResponse.of("Access denied!")

        // act
        val request = MockMvcRequestBuilders.get("/api/user")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .authorizationHeader(token)

        val response = mockMvc.perform(request).andReturn().response

        // assert
        response.status shouldBe HttpStatus.FORBIDDEN.value()
        response.contentType shouldBe MediaType.APPLICATION_JSON_VALUE
        response.contentAsString shouldBeEqualJson expected
    }

    @Test
    fun `getCurrentUser should return forbidden error when token is null`() {
        // arrange
        val expected = ErrorResponse.of("Access denied!")

        // act
        val request = MockMvcRequestBuilders.get("/api/user")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .authorizationHeader(null)

        val response = mockMvc.perform(request).andReturn().response

        // assert
        response.status shouldBe HttpStatus.FORBIDDEN.value()
        response.contentType shouldBe MediaType.APPLICATION_JSON_VALUE
        response.contentAsString shouldBeEqualJson expected
    }
}
