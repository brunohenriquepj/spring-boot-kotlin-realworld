package com.example.realworld.util

interface TokenUtil {
    fun generateToken(userEmail: String): String
    fun getTokenSubject(token: String?): String?
}