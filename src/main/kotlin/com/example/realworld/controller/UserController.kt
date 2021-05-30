package com.example.realworld.controller

import com.example.realworld.dto.user.request.CreateUserRequest
import com.example.realworld.dto.user.request.LoginRequest
import com.example.realworld.dto.user.response.CreateUserResponse
import com.example.realworld.dto.user.response.LoginResponse
import com.example.realworld.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {
    @PostMapping
    fun registration(
        @RequestBody @Valid request: CreateUserRequest,
        @Autowired uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<CreateUserResponse> {
        val response = CreateUserResponse(userService.create(request.user!!))

        val uri = uriComponentsBuilder.path("/api/users").build().toUri()
        return ResponseEntity.created(uri).body(response)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid request: LoginRequest): LoginResponse {
        val response = this.userService.login(request.user!!)
        return LoginResponse(response)
    }
}
