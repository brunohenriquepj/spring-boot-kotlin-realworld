package com.example.realworld.controller

import com.example.realworld.dto.user.request.CreateUserRequest
import com.example.realworld.dto.user.response.CreateUserResponse
import com.example.realworld.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UsersController(val userService: UserService) {
    @PostMapping
    fun registration(@RequestBody request: CreateUserRequest): ResponseEntity<CreateUserResponse> {
        val response = CreateUserResponse(userService.create(request.user))
        return ResponseEntity.ok(response)
    }
}