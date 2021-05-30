package com.example.realworld.repository

import com.example.realworld.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmailOrUserName(email: String, userName: String): User?
    fun findByEmail(email: String): User?
}