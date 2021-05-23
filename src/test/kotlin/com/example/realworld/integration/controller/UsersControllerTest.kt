package com.example.realworld.integration.controller

import com.example.realworld.dto.user.request.CreateUserRequest
import com.example.realworld.dto.user.response.CreateUserResponse
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.util.builder.user.CreateUserRequestDataBuilder
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UsersControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun registerProperties(propertyRegistry: DynamicPropertyRegistry) {
            propertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl)
            propertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername)
            propertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword)
        }

        @JvmStatic
        @Container
        private val mySQLContainer = MySQLContainer<Nothing>("mysql:8")
            .apply {
                withDatabaseName("testDb")
                withUsername("test")
                withPassword("password")
                start()
            }
    }

    @Test
    fun test() {
        // arrange
        val request = CreateUserRequest(CreateUserRequestDataBuilder().build())

        val expected = CreateUserResponseData(
            userName = request.user?.userName!!,
            email = request.user?.email!!,
            token = "TODO: add jwt token",
            bio = null,
            image = null
        )

        // act
        val response = restTemplate.postForEntity<CreateUserResponse>(
            "/api/users", request, CreateUserRequest::class.java
        )

        // assert
        response.statusCode shouldBe HttpStatus.CREATED
        response.body shouldNotBe null
        response.body!!.user shouldBeEqualToComparingFields expected
    }
}

