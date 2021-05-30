package com.example.realworld.service.implementation

import com.example.realworld.adapter.CreateUserRequestDataAdapter
import com.example.realworld.adapter.LoginRequestDataAdapter
import com.example.realworld.adapter.UserAdapter
import com.example.realworld.dto.user.request.CreateUserRequestData
import com.example.realworld.dto.user.request.LoginRequestData
import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.dto.user.response.GetCurrentUserResponseData
import com.example.realworld.dto.user.response.LoginResponseData
import com.example.realworld.exception.BusinessValidationException
import com.example.realworld.repository.UserRepository
import com.example.realworld.service.SecurityContextService
import com.example.realworld.service.UserService
import com.example.realworld.util.TokenUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val tokenUtil: TokenUtil,
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val createUserRequestDataAdapter: CreateUserRequestDataAdapter,
    private val userAdapter: UserAdapter,
    private val loginRequestDataAdapter: LoginRequestDataAdapter,
    private val securityContextService: SecurityContextService
) : UserService {
    override fun create(request: CreateUserRequestData): CreateUserResponseData {
        val existentUser =
            userRepository.findByEmailOrUserName(request.email, request.userName)
        if (existentUser == null) {
            val createdUser = userRepository.save(createUserRequestDataAdapter.toUser(request))
            val authenticationToken = tokenUtil.generateToken(createdUser.email)
            return userAdapter.toCreateUserResponseData(createdUser, authenticationToken)
        }

        throw BusinessValidationException("User already exists!")
    }

    override fun login(request: LoginRequestData): LoginResponseData {
        val authenticationData = loginRequestDataAdapter.toUsernamePasswordAuthenticationToken(request)
        authenticationManager.authenticate(authenticationData)

        val user = userRepository.findByEmail(request.email)
        val authenticationToken = tokenUtil.generateToken(user!!.email)
        return userAdapter.toLoginResponseData(user, authenticationToken)
    }

    override fun getCurrentUser(): GetCurrentUserResponseData {
        val userDetails = securityContextService.getPrincipal()
        val user = userRepository.findByEmail(userDetails.username)

        return GetCurrentUserResponseData(
            userName = user!!.userName,
            email = user.email,
            bio = user.bio,
            image = user.image
        )
    }
}