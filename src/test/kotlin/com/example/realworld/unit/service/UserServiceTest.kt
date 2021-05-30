package com.example.realworld.unit.service

import com.example.realworld.adapter.CreateUserRequestDataAdapter
import com.example.realworld.adapter.LoginRequestDataAdapter
import com.example.realworld.adapter.UserAdapter
import com.example.realworld.dto.user.response.GetCurrentUserResponseData
import com.example.realworld.exception.BusinessValidationException
import com.example.realworld.repository.UserRepository
import com.example.realworld.service.SecurityContextService
import com.example.realworld.service.UserService
import com.example.realworld.service.implementation.UserServiceImpl
import com.example.realworld.util.TokenUtil
import com.example.realworld.util.builder.common.StringBuilder
import com.example.realworld.util.builder.user.*
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.security.authentication.AuthenticationManager

class UserServiceTest {
    private val tokenUtilMock: TokenUtil = mockk()
    private val userAdapterMock: UserAdapter = mockk()
    private val createUserRequestDataAdapterMock: CreateUserRequestDataAdapter = mockk()
    private val userRepositoryMock: UserRepository = mockk()
    private val authenticationManagerMock: AuthenticationManager = mockk()
    private val loginRequestDataAdapterMock: LoginRequestDataAdapter = mockk()
    private val securityContextServiceMock: SecurityContextService = mockk()
    private val service: UserService

    init {
        service = UserServiceImpl(
            tokenUtilMock,
            authenticationManagerMock,
            userRepositoryMock,
            createUserRequestDataAdapterMock,
            userAdapterMock,
            loginRequestDataAdapterMock,
            securityContextServiceMock
        )
    }

    @Test
    fun `create should return CreateUserResponseData when create User`() {
        // arrange
        val requestData = CreateUserRequestDataBuilder().build()
        val user = UserBuilder().build()
        val createdUser = UserBuilder().build()
        val responseData = CreateUserResponseDataBuilder().build()
        val authenticationToken = StringBuilder().build()

        every { userRepositoryMock.findByEmailOrUserName(requestData.email, requestData.userName) } returns null
        every { createUserRequestDataAdapterMock.toUser(requestData) } returns user
        every { userRepositoryMock.save(user) } returns createdUser
        every { tokenUtilMock.generateToken(createdUser.email) } returns authenticationToken
        every { userAdapterMock.toCreateUserResponseData(createdUser, authenticationToken) } returns responseData

        val expected = responseData.copy()

        // act
        val actual = service.create(requestData)

        // assert
        verify(exactly = 1) {
            userRepositoryMock.findByEmailOrUserName(requestData.email, requestData.userName)
            createUserRequestDataAdapterMock.toUser(requestData)
            userRepositoryMock.save(user)
            userAdapterMock.toCreateUserResponseData(createdUser, authenticationToken)
            tokenUtilMock.generateToken(createdUser.email)
        }

        actual shouldBeEqualToComparingFields expected
    }

    @Test
    fun `create should throws exception when User exist`() {
        // arrange
        val requestData = CreateUserRequestDataBuilder().build()
        val existentUser = UserBuilder().build()

        every { userRepositoryMock.findByEmailOrUserName(requestData.email, requestData.userName) } returns existentUser

        val expected = BusinessValidationException("User already exists!")

        // act
        val actual = shouldThrowExactly<BusinessValidationException> {
            service.create(requestData)
        }

        // assert
        verify(exactly = 1) {
            userRepositoryMock.findByEmailOrUserName(requestData.email, requestData.userName)
        }

        verify(exactly = 0) {
            createUserRequestDataAdapterMock.toUser(any())
            userRepositoryMock.save(any())
            userAdapterMock.toCreateUserResponseData(any(), any())
        }

        actual.message shouldBe expected.message
    }

    @Test
    fun `login should return LoginResponseData`() {
        // arrange
        val request = LoginRequestDataBuilder().build()

        val authenticationData = UsernamePasswordAuthenticationTokenBuilder().build()
        every { loginRequestDataAdapterMock.toUsernamePasswordAuthenticationToken(request) } returns authenticationData
        every { authenticationManagerMock.authenticate(authenticationData) } returns null

        val user = UserBuilder().build()
        every { userRepositoryMock.findByEmail(request.email) } returns user

        val authenticationToken = StringBuilder().build()
        every { tokenUtilMock.generateToken(user.email) } returns authenticationToken

        val loginResponseData = LoginResponseDataBuilder().build()
        every { userAdapterMock.toLoginResponseData(user, authenticationToken) } returns loginResponseData

        // act
        val actual = service.login(request)

        // assert
        verify(exactly = 1) {
            loginRequestDataAdapterMock.toUsernamePasswordAuthenticationToken(request)
            authenticationManagerMock.authenticate(authenticationData)
            userRepositoryMock.findByEmail(request.email)
            tokenUtilMock.generateToken(user.email)
            userAdapterMock.toLoginResponseData(user, authenticationToken)
        }

        actual shouldBeEqualToComparingFields loginResponseData
    }

    @Test
    fun `getCurrentUser return GetCurrentUserResponseData`() {
        // arrange
        val userDetails = UserDetailsBuilder().build()
        every { securityContextServiceMock.getPrincipal() } returns userDetails

        val user = UserBuilder().build()
        every { userRepositoryMock.findByEmail(userDetails.username) } returns user

        val expected = GetCurrentUserResponseData(user.userName, user.email, user.bio, user.image)

        // act
        val actual = service.getCurrentUser()

        // assert
        actual shouldBe expected
    }
}

