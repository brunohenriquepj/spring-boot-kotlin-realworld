package com.example.realworld.service

import com.example.realworld.entity.User

interface AuthenticationTokenService {
    fun generateToken(user: User) : String
}