package com.example.realworld.repository

import com.example.realworld.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmailOrUserName(email: String, userName: String): User?
    fun findByUserName(username: String): User?
}