package com.example.realworld.adapter.implementation

import com.example.realworld.adapter.LoginRequestDataAdapter
import com.example.realworld.annotation.PrototypeScopeComponent
import com.example.realworld.dto.user.request.LoginRequestData
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class LoginRequestDataAdapterImpl : LoginRequestDataAdapter {
    override fun toUsernamePasswordAuthenticationToken(request: LoginRequestData): UsernamePasswordAuthenticationToken {
        return UsernamePasswordAuthenticationToken(request.email, request.password)
    }
}