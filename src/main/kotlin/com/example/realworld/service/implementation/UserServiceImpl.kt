package com.example.realworld.service.implementation

import com.example.realworld.adapter.CreateUserRequestDataAdapter
import com.example.realworld.adapter.UserAdapter
import com.example.realworld.dto.user.request.CreateUserRequestData
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.exception.BusinessValidationException
import com.example.realworld.repository.UserRepository
import com.example.realworld.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val createUserRequestDataAdapter: CreateUserRequestDataAdapter,
    val userAdapter: UserAdapter
) : UserService {
    override fun create(createUserRequestData: CreateUserRequestData): CreateUserResponseData {
        val existentUser =
            userRepository.findByEmailOrUserName(createUserRequestData.email, createUserRequestData.userName)
        if (existentUser == null) {
            val createdUser = userRepository.save(createUserRequestDataAdapter.toUser(createUserRequestData))
            return userAdapter.toCreateUserResponseData(createdUser)
        }

        throw BusinessValidationException("User already exists!")
    }
}