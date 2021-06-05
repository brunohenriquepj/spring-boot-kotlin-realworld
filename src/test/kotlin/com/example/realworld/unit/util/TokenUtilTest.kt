package com.example.realworld.unit.util

import com.example.realworld.util.builder.common.EmailBuilder
import com.example.realworld.util.builder.common.StringBuilder
import com.example.realworld.util.implementation.TokenUtilImpl
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TokenUtilTest {
    private val tokenUtil = TokenUtilImpl(secret = StringBuilder().build(), expiration = 5000)

    @Test
    fun `getTokenSubject should return subject`() {
        // arrange
        val email = EmailBuilder().build()
        val token = tokenUtil.generateToken(email)

        // act
        val subject = tokenUtil.getTokenSubject(token)

        // assert
        subject shouldBe email
    }

    @Test
    fun `getTokenSubject should return null when token is invalid`() {
        // arrange
        val email = EmailBuilder().build()
        val token = tokenUtil.generateToken(email).dropLast(3)

        // act
        val subject = tokenUtil.getTokenSubject(token)

        // assert
        subject shouldBe null
    }

    @Test
    fun `getTokenSubject should return null when token is null`() {
        // act
        val subject = tokenUtil.getTokenSubject(null)

        // assert
        subject shouldBe null
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "   ", " "])
    fun `getTokenSubject should return null when token is blank`(token: String) {
        // act
        val subject = tokenUtil.getTokenSubject(token)

        // assert
        subject shouldBe null
    }
}