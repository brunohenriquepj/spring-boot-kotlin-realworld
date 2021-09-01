package com.example.realworld.adapter

import com.example.realworld.dto.user.request.UpdateUserRequestData
import com.example.realworld.entity.User

interface UpdateUserRequestDataAdapter {
    fun toUser(request: UpdateUserRequestData): User
}
