package com.example.realworld.filter

import com.example.realworld.entity.UserDetailsImpl
import com.example.realworld.repository.UserRepository
import com.example.realworld.service.SecurityContextService
import com.example.realworld.util.TokenUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val tokenUtil: TokenUtil,
    private val userRepository: UserRepository,
    private val securityContextService: SecurityContextService
) : BasicAuthenticationFilter(authenticationManager) {

    private companion object {
        const val tokenPrefix = "Bearer "
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = getToken(request)
        if (token.isNullOrBlank()) {
            filterChain.doFilter(request, response)
            return
        }

        val userEmail = tokenUtil.getTokenSubject(token)
        if (userEmail == null) {
            filterChain.doFilter(request, response)
            return
        }

        authenticateUser(userEmail)
        filterChain.doFilter(request, response)
    }

    private fun authenticateUser(userEmail: String) {
        val user = userRepository.findByEmail(userEmail)
        if (user == null) return

        val userDetails = UserDetailsImpl(user)
        val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        securityContextService.setAuthentication(authentication)
    }

    private fun getToken(request: HttpServletRequest): String? {
        val header = request.getHeader("Authorization")

        if (header.isNullOrBlank() || !header.startsWith(tokenPrefix)) {
            return null
        }
        return header.substring(tokenPrefix.length)
    }
}
