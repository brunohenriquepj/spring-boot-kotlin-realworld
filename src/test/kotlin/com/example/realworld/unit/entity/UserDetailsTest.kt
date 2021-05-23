package com.example.realworld.unit.entity

import com.example.realworld.entity.UserDetailsImpl
import com.example.realworld.util.builder.user.UserBuilder
import io.kotest.matchers.be
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class UserDetailsTest {
    @Test
    fun `user details should set values`() {
        // arrange
        val user = UserBuilder().build()

        // act
        val actual = UserDetailsImpl(user)

        // assert
        actual.username shouldBe user.userName
        actual.password shouldBe user.password
        actual.authorities shouldBe mutableListOf()
        actual.isAccountNonExpired shouldBe true
        actual.isAccountNonLocked shouldBe true
        actual.isCredentialsNonExpired shouldBe true
        actual.isEnabled shouldBe true
    }
}