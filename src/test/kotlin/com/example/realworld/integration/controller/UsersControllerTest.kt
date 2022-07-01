package com.example.realworld.integration.controller

import com.example.realworld.dto.user.request.CreateUserRequest
import com.example.realworld.dto.user.response.CreateUserResponse
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.util.annotation.SpringBootIntegrationTest
import com.example.realworld.util.builder.user.CreateUserRequestDataBuilder
import io.kotest.matchers.equality.FieldsEqualityCheckConfig
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.string.beEmpty
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus


@SpringBootIntegrationTest
class UsersControllerTest {

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
        val actualUser = response.body!!.user!!

        // assert
        response.statusCode shouldBe HttpStatus.CREATED
        actualUser.shouldBeEqualToComparingFields(expected, FieldsEqualityCheckConfig(propertiesToExclude = listOf(CreateUserResponseData::token)))
        actualUser.token.trim() shouldNot beEmpty()
    }
}
