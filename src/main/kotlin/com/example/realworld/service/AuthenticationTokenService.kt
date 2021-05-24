package com.example.realworld.service

interface AuthenticationTokenService {
    fun generateToken(userEmail: String) : String
}