package com.example.realworld.adapter

import com.example.realworld.dto.user.request.LoginRequestData
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

interface LoginRequestDataAdapter {
    fun toUsernamePasswordAuthenticationToken(request: LoginRequestData): UsernamePasswordAuthenticationToken
}