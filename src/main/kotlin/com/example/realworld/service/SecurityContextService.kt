package com.example.realworld.service

import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails

interface SecurityContextService {
    fun setAuthentication(authentication: Authentication)
    fun getPrincipal():UserDetails
}