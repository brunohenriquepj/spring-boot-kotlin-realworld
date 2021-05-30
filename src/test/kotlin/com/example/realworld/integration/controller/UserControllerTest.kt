package com.example.realworld.integration.controller

import com.example.realworld.dto.user.request.CreateUserRequest
import com.example.realworld.dto.user.response.CreateUserResponse
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.util.IntegrationTest
import com.example.realworld.util.builder.user.CreateUserRequestDataBuilder
import io.kotest.matchers.equality.shouldBeEqualToComparingFieldsExcept
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.string.beEmpty
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus

@IntegrationTest
class UserControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `registration should return user response when create user`() {
        // arrange
        val request = CreateUserRequest(CreateUserRequestDataBuilder().build())

        val expected = CreateUserResponseData(
            userName = request.user?.userName!!,
            email = request.user?.email!!,
            bio = null,
            image = null
        )

        // act
        val response = restTemplate.postForEntity<CreateUserResponse>(
            "/api/users", request, CreateUserRequest::class.java
        )

        // assert
        response.statusCode shouldBe HttpStatus.CREATED
        response.body!!.user.shouldBeEqualToComparingFieldsExcept(expected, CreateUserResponseData::token)
        response.body!!.user.token.trim() shouldNot beEmpty()
    }
}
