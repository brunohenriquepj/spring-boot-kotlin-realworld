package com.example.realworld.integration.controller

import com.example.realworld.dto.user.response.GetCurrentUserResponse
import com.example.realworld.dto.user.response.GetCurrentUserResponseData
import com.example.realworld.repository.UserRepository
import com.example.realworld.util.annotation.SpringBootIntegrationTest
import com.example.realworld.util.TokenUtil
import com.example.realworld.util.builder.user.UserBuilder
import com.example.realworld.util.extension.getForEntity
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootIntegrationTest
class UserInfoControllerTest {
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var tokenUtil: TokenUtil

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
}
