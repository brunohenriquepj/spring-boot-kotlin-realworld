package com.example.realworld.unit.service

import com.example.realworld.adapter.CreateUserRequestDataAdapter
import com.example.realworld.adapter.LoginRequestDataAdapter
import com.example.realworld.adapter.UpdateUserRequestDataAdapter
import com.example.realworld.adapter.UserAdapter
import com.example.realworld.dto.user.response.GetCurrentUserResponseData
import com.example.realworld.dto.user.response.UpdateUserResponseData
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
import io.mockk.*
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
    private val updateUserRequestDataAdapterMock: UpdateUserRequestDataAdapter = mockk()
    private val service: UserService

    init {
        service = UserServiceImpl(
            tokenUtilMock,
            authenticationManagerMock,
            userRepositoryMock,
            createUserRequestDataAdapterMock,
            userAdapterMock,
            loginRequestDataAdapterMock,
            securityContextServiceMock,
            updateUserRequestDataAdapterMock
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
        every { userRepositoryMock.getByEmail(request.email) } returns user

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
            userRepositoryMock.getByEmail(request.email)
            tokenUtilMock.generateToken(user.email)
            userAdapterMock.toLoginResponseData(user, authenticationToken)
        }

        actual shouldBeEqualToComparingFields loginResponseData
    }

    @Test
    fun `getCurrentUser should return GetCurrentUserResponseData`() {
        // arrange
        val userDetails = UserDetailsBuilder().build()
        every { securityContextServiceMock.getPrincipal() } returns userDetails

        val user = UserBuilder().build()
        every { userRepositoryMock.getByEmail(userDetails.username) } returns user

        val expected = GetCurrentUserResponseData(user.userName, user.email, user.bio, user.image)

        // act
        val actual = service.getCurrentUser()

        // assert
        verify(exactly = 1) {
            securityContextServiceMock.getPrincipal()
            userRepositoryMock.getByEmail(userDetails.username)
        }

        actual shouldBe expected
    }

    @Test
    fun `update should update and authenticate user and return UpdateUserResponseData`() {
        // arrange
        val request = UpdateUserRequestDataBuilder().build()

        val userDetails = UserDetailsBuilder().build()
        every { securityContextServiceMock.getPrincipal() } returns userDetails

        val authenticatedUser = UserBuilder().build()
        every { userRepositoryMock.getByEmail(userDetails.username) } returns authenticatedUser

        val existentUser = UserBuilder().build()
        every { userRepositoryMock.findByEmailOrUserName(request.email, request.userName) } returns existentUser

        val updatedUser = UserBuilder().build()
        every { updateUserRequestDataAdapterMock.toUser(request) } returns updatedUser

        every { userRepositoryMock.update(authenticatedUser.id, updatedUser) } just runs

        val authenticationToken = StringBuilder().build()
        every { tokenUtilMock.generateToken(updatedUser.email) } returns authenticationToken

        val response = UpdateUserResponseDataBuilder().build()
        every { userAdapterMock.toUpdateUserResponseData(updatedUser, authenticationToken) } returns response

        val expected = UpdateUserResponseData(
            userName = response.userName,
            email = response.email,
            bio = response.bio,
            image = response.image,
            token = response.token
        )

        // act
        val actual = service.update(request)

        // assert
        verify(exactly = 1) {
            securityContextServiceMock.getPrincipal()
            userRepositoryMock.getByEmail(userDetails.username)
            userRepositoryMock.findByEmailOrUserName(request.email, request.userName)
            updateUserRequestDataAdapterMock.toUser(request)
            userRepositoryMock.update(authenticatedUser.id, updatedUser)
            tokenUtilMock.generateToken(updatedUser.email)
            userAdapterMock.toUpdateUserResponseData(updatedUser, authenticationToken)
        }

        actual shouldBe expected
    }

    @Test
    fun `update should throws exception when exists an user with request email`() {
        // arrange
        val request = UpdateUserRequestDataBuilder().build()

        val userDetails = UserDetailsBuilder().build()
        every { securityContextServiceMock.getPrincipal() } returns userDetails

        val authenticatedUser = UserBuilder().build()
        every { userRepositoryMock.getByEmail(userDetails.username) } returns authenticatedUser

        val existentUser = UserBuilder()
            .apply {
                email = request.email
            }
            .build()
        every { userRepositoryMock.findByEmailOrUserName(request.email, request.userName) } returns existentUser

        val expected = BusinessValidationException("Email already exists!")

        // act
        val actual = shouldThrowExactly<BusinessValidationException> {
            service.update(request)
        }

        // assert
        verify(exactly = 1) {
            securityContextServiceMock.getPrincipal()
            userRepositoryMock.getByEmail(userDetails.username)
            userRepositoryMock.findByEmailOrUserName(request.email, request.userName)
        }

        verify(exactly = 0) {
            updateUserRequestDataAdapterMock.toUser(any())
            userRepositoryMock.update(any(), any())
            tokenUtilMock.generateToken(any())
            userAdapterMock.toUpdateUserResponseData(any(), any())
        }

        actual shouldBe expected
    }

    @Test
    fun `update should throws exception when exists an user with request userName`() {
        // arrange
        val request = UpdateUserRequestDataBuilder().build()

        val userDetails = UserDetailsBuilder().build()
        every { securityContextServiceMock.getPrincipal() } returns userDetails

        val authenticatedUser = UserBuilder().build()
        every { userRepositoryMock.getByEmail(userDetails.username) } returns authenticatedUser

        val existentUser = UserBuilder()
            .apply {
                userName = request.userName
            }
            .build()
        every { userRepositoryMock.findByEmailOrUserName(request.email, request.userName) } returns existentUser

        val expected = BusinessValidationException("Username already exists!")

        // act
        val actual = shouldThrowExactly<BusinessValidationException> {
            service.update(request)
        }

        // assert
        verify(exactly = 1) {
            securityContextServiceMock.getPrincipal()
            userRepositoryMock.getByEmail(userDetails.username)
            userRepositoryMock.findByEmailOrUserName(request.email, request.userName)
        }

        verify(exactly = 0) {
            updateUserRequestDataAdapterMock.toUser(any())
            userRepositoryMock.update(any(), any())
            tokenUtilMock.generateToken(any())
            userAdapterMock.toUpdateUserResponseData(any(), any())
        }

        actual shouldBe expected
    }
}

