package com.example.realworld.adapter

import com.example.realworld.dto.user.response.UpdateUserResponseData
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.dto.user.response.LoginResponseData
import com.example.realworld.entity.User

interface UserAdapter {
    fun toCreateUserResponseData(user: User, authenticationToken: String): CreateUserResponseData
    fun toLoginResponseData(user: User, authenticationToken: String): LoginResponseData
    fun toUpdateUserResponseData(user: User, authenticationToken: String): UpdateUserResponseData
}
