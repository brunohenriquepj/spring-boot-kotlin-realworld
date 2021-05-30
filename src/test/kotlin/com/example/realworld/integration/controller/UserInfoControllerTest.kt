package com.example.realworld.integration.controller

import com.example.realworld.dto.ErrorResponse
import com.example.realworld.dto.user.response.GetCurrentUserResponse
import com.example.realworld.dto.user.response.GetCurrentUserResponseData
import com.example.realworld.repository.UserRepository
import com.example.realworld.util.IntegrationTest
import com.example.realworld.util.TokenUtil
import com.example.realworld.util.builder.user.UserBuilder
import com.example.realworld.util.getForEntity
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@IntegrationTest
@Disabled // TODO: test with request scoped component
class UserInfoControllerTest {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var tokenUtil: TokenUtil

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `getCurrentUser should return user response when get current user`() {
        // arrange
        val user = UserBuilder().build()

        val expected = GetCurrentUserResponse(
            user = GetCurrentUserResponseData(
                userName = user.userName,
                email = user.email,
                bio = user.bio,
                image = user.image
            )
        )

        val token = tokenUtil.generateToken(user.email)
        userRepository.save(user)

        // act
        val response = restTemplate
            .getForEntity<GetCurrentUserResponse>(uri = "/api/user", token = token)

        // assert
        response.statusCode shouldBe HttpStatus.OK
        response.body!! shouldBeEqualToComparingFields expected
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJSZWFsV29ybGQgQXBpIiwic3ViIjoiaGVucnkub3Jkb25oZXNAZ21haWwuY29tIiwiaWF0IjoxNjIyMzY0MDY0LCJleHAiOjE2MjIzNjQxMjR9.03ELoPPeCy6hS9KPW1_bNEc3wIHWdMPNi8Mrt0jEGU_0x677s16AYCzmdzAl-qEliDxFeI4KMwtfM0KRlvZk1Q",
        "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJSZWFsV29ybGQgQXBpIiwic3ViIjoibWFyaWFoLnZpZWlyYUBsaXZlLmNvbSIsImlhdCI6MTYyMjM2NDE0MywiZXhwIjoxNjIyMzY0MjAzfQ.HHKkchLViZiBdpM9RT3etShKeAFOS_AvuOGRYTsHcMduinUQIYxODq7pCx4dvb_EZytNMZSQ3CSPvDrwyiJtBg",
        "",
        "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJSZWFsV29ybGQgQXBpIiwic3ViIjoibWFyaWFoLnZpZWlyYUBsaXZlLmNvbSIsImlhdCI6MTYyMjM2NDE0MywiZXhwIjoxNjIyMzY0MjAzfQ"
    ])
    fun `getCurrentUser should return forbidden error when token is invalid`(token: String) {
        // arrange
        val expected = ErrorResponse.of("Access denied!")

        // act
        val response = restTemplate
            .getForEntity<ErrorResponse>(uri = "/api/user", token = token)

        // assert
        response.statusCode shouldBe HttpStatus.FORBIDDEN
        response.body!!.errors.body.toList() shouldContainAll expected.errors.body.toList()
    }

    @Test
    fun `getCurrentUser should return forbidden error when token is null`() {
        // arrange
        val expected = ErrorResponse.of("Access denied!")

        // act
        val response = restTemplate
            .getForEntity<ErrorResponse>(uri = "/api/user", token = null)

        // assert
        response.statusCode shouldBe HttpStatus.FORBIDDEN
        response.body!!.errors.body.toList() shouldContainAll expected.errors.body.toList()
    }
}
