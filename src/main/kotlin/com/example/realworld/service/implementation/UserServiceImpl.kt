package com.example.realworld.service.implementation

import com.example.realworld.adapter.CreateUserRequestDataAdapter
import com.example.realworld.adapter.LoginRequestDataAdapter
import com.example.realworld.adapter.UserAdapter
import com.example.realworld.dto.user.request.CreateUserRequestData
import com.example.realworld.dto.user.request.LoginRequestData
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.dto.user.response.LoginResponseData
import com.example.realworld.exception.BusinessValidationException
import com.example.realworld.repository.UserRepository
import com.example.realworld.service.AuthenticationTokenService
import com.example.realworld.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val authenticationTokenService: AuthenticationTokenService,
    val authenticationManager: AuthenticationManager,
    val userRepository: UserRepository,
    val createUserRequestDataAdapter: CreateUserRequestDataAdapter,
    val userAdapter: UserAdapter,
    val loginRequestDataAdapter: LoginRequestDataAdapter
) : UserService {
    override fun create(request: CreateUserRequestData): CreateUserResponseData {
        val existentUser =
            userRepository.findByEmailOrUserName(request.email, request.userName)
        if (existentUser == null) {
            val createdUser = userRepository.save(createUserRequestDataAdapter.toUser(request))
            val authenticationToken = authenticationTokenService.generateToken(createdUser.email)
            return userAdapter.toCreateUserResponseData(createdUser, authenticationToken)
        }

        throw BusinessValidationException("User already exists!")
    }

    override fun login(request: LoginRequestData): LoginResponseData {
        val authenticationData = loginRequestDataAdapter.toUsernamePasswordAuthenticationToken(request)
        authenticationManager.authenticate(authenticationData)

        val user = userRepository.findByEmail(request.email)
        val authenticationToken = this.authenticationTokenService.generateToken(user!!.email)
        return userAdapter.toLoginResponseData(user, authenticationToken)
    }
}