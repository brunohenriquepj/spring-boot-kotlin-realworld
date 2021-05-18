package com.example.realworld.unit.service

import com.example.realworld.adapter.CreateUserRequestDataAdapter
import com.example.realworld.adapter.UserAdapter
import com.example.realworld.exception.BusinessValidationException
import com.example.realworld.repository.UserRepository
import com.example.realworld.service.UserService
import com.example.realworld.service.implementation.UserServiceImpl
import com.example.realworld.util.builder.user.CreateUserRequestDataBuilder
import com.example.realworld.util.builder.user.CreateUserResponseDataBuilder
import com.example.realworld.util.builder.user.UserBuilder
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class UserServiceTest {
    private var userAdapterMock: UserAdapter
    private var createUserRequestDataAdapterMock: CreateUserRequestDataAdapter
    private var userRepositoryMock: UserRepository
    private val service: UserService

    init {
        userRepositoryMock = mockk<UserRepository>()
        createUserRequestDataAdapterMock = mockk<CreateUserRequestDataAdapter>()
        userAdapterMock = mockk<UserAdapter>()

        service = UserServiceImpl(userRepositoryMock, createUserRequestDataAdapterMock, userAdapterMock)
    }

    @Test
    fun `create should return CreateUserResponseData when create User`() {
        // arrange
        val requestData = CreateUserRequestDataBuilder().build()
        val user = UserBuilder().build()
        val createdUser = UserBuilder().build()
        val responseData = CreateUserResponseDataBuilder().build()

        every { userRepositoryMock.findByEmailOrUserName(requestData.email, requestData.userName) } returns null
        every { createUserRequestDataAdapterMock.toUser(requestData) } returns user
        every { userRepositoryMock.save(user) } returns createdUser
        every { userAdapterMock.toCreateUserResponseData(createdUser) } returns responseData

        val expected = responseData.copy()

        // act
        val actual = service.create(requestData)

        // assert
        verify(exactly = 1) {
            userRepositoryMock.findByEmailOrUserName(requestData.email, requestData.userName)
            createUserRequestDataAdapterMock.toUser(requestData)
            userRepositoryMock.save(user)
            userAdapterMock.toCreateUserResponseData(createdUser)
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
            userAdapterMock.toCreateUserResponseData(any())
        }

        actual.message shouldBe expected.message
    }
}