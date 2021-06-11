package com.example.realworld.unit.service

import com.example.realworld.service.implementation.SecurityContextServiceImpl
import com.example.realworld.util.builder.user.UserDetailsBuilder
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder

class SecurityContextServiceTest {
    private val securityContextService = SecurityContextServiceImpl()
    private val securityContextMock = mockk<SecurityContext>()

    @BeforeEach
    fun setUp() {
        mockkStatic(SecurityContextHolder::class)
        every { SecurityContextHolder.getContext() } returns securityContextMock
    }

    @Test
    fun `setAuthentication should set Authentication`() {
        // arrange
        val securityContextSlot = slot<UsernamePasswordAuthenticationToken>()
        every { securityContextMock.authentication = capture(securityContextSlot) } just runs

        val userDetails = UserDetailsBuilder().build()
        val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)

        // act
        securityContextService.setAuthentication(authentication)

        // assert
        securityContextSlot.captured shouldBeEqualToComparingFields authentication
    }

    @Test
    fun `getPrincipal should return UserDetails`() {
        // arrange
        val userDetails = UserDetailsBuilder().build()
        val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        every { securityContextMock.authentication } returns authentication

        // act
        val actual = securityContextService.getPrincipal()

        // assert
        actual shouldBeEqualToComparingFields authentication
    }
}
