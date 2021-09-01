package com.example.realworld.integration.controller

import com.example.realworld.dto.user.request.UpdateUserRequest
import com.example.realworld.dto.user.response.UpdateUserResponse
import com.example.realworld.dto.user.response.UpdateUserResponseData
import com.example.realworld.dto.user.response.GetCurrentUserResponse
import com.example.realworld.dto.user.response.GetCurrentUserResponseData
import com.example.realworld.repository.UserRepository
import com.example.realworld.util.TokenUtil
import com.example.realworld.util.annotation.SpringBootIntegrationTest
import com.example.realworld.util.builder.user.UpdateUserRequestDataBuilder
import com.example.realworld.util.builder.user.UserBuilder
import com.example.realworld.util.extension.getForEntity
import com.example.realworld.util.extension.putForEntity
import io.kotest.assertions.asClue
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.equality.shouldBeEqualToComparingFieldsExcept
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.beEmpty
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootIntegrationTest
class UserControllerTest {
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

    @Test
    fun `updateUser should return user response when update user`() {
        // arrange
        val request = UpdateUserRequest(UpdateUserRequestDataBuilder().build())

        val expected = UpdateUserResponseData(
            userName = request.user!!.userName,
            email = request.user!!.email,
            bio = request.user!!.bio,
            image = request.user!!.image
        )

        val user = UserBuilder().build()
        val token = tokenUtil.generateToken(user.email)
        userRepository.save(user)

        // act
        val response = restTemplate
            .putForEntity<UpdateUserResponse>(uri = "/api/user", request, token = token)
        val actualUser = response.body!!.user!!
        val actualSubject = tokenUtil.getTokenSubject(actualUser.token)

        // assert
        response.statusCode shouldBe HttpStatus.OK
        actualUser.shouldBeEqualToComparingFieldsExcept(expected, UpdateUserResponseData::token)
        actualUser.token.asClue {
            it shouldNot beEmpty()
            it shouldNotBe token
        }
        actualSubject shouldBe expected.email
    }
}

