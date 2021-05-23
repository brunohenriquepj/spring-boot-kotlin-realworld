package com.example.realworld.adapter

import com.example.realworld.dto.user.request.CreateUserRequestData
import com.example.realworld.entity.User

interface CreateUserRequestDataAdapter {
    fun toUser(createUserRequestData: CreateUserRequestData): User
}