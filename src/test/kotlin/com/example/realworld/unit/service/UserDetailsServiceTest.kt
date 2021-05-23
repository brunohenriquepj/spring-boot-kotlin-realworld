package com.example.realworld.unit.service

import com.example.realworld.entity.UserDetailsImpl
import com.example.realworld.repository.UserRepository
import com.example.realworld.service.implementation.UserDetailsServiceImpl
import com.example.realworld.util.builder.user.UserBuilder
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailsServiceTest {
    private val userRepositoryMock = mockk<UserRepository>()
    private val userDetailsService = UserDetailsServiceImpl(userRepositoryMock)

    @Test
    fun `loadUserByUsername should return user details when user exists`() {
        // arrange
        val user = UserBuilder().build()
        val userName = user.userName

        every { userRepositoryMock.findByUserName(userName) } returns user

        val expected = UserDetailsImpl(user)

        // act
        val actual = userDetailsService.loadUserByUsername(userName)


        // assert
        verify(exactly = 1) {
            userRepositoryMock.findByUserName(userName)
        }

        actual shouldBeEqualToComparingFields expected
    }

    @Test
    fun `loadUserByUsername should throw exception when user does not exist`() {
        // arrange
        val userName = UserBuilder().build().userName

        every { userRepositoryMock.findByUserName(userName) } returns null

        // act
        val actual = shouldThrowExactly<UsernameNotFoundException> {
            userDetailsService.loadUserByUsername(userName)
        }


        // assert
        verify(exactly = 1) {
            userRepositoryMock.findByUserName(userName)
        }

        actual.message shouldBe "Username or password invalid!"
    }
}