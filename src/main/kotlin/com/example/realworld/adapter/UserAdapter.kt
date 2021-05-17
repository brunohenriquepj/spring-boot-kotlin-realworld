package com.example.realworld.adapter

import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.entity.User

interface UserAdapter {
    fun toCreateUserResponseData(user: User): CreateUserResponseData
}