package com.example.realworld.service.implementation

import com.example.realworld.annotation.RequestScopeComponent
import com.example.realworld.service.SecurityContextService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

@RequestScopeComponent
class SecurityContextServiceImpl : SecurityContextService {
    override fun setAuthentication(authentication: Authentication) {
        getContext().authentication = authentication
    }

    override fun getPrincipal(): UserDetails {
        return getContext().authentication.principal as UserDetails
    }

    private fun getContext(): SecurityContext {
        return SecurityContextHolder.getContext()
    }
}