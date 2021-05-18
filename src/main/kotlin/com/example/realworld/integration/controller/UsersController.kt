package com.example.realworld.integration.controller

import com.example.realworld.dto.user.request.CreateUserRequest
import com.example.realworld.dto.user.response.CreateUserResponse
import com.example.realworld.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/api/users")
class UsersController(val userService: UserService) {
    @PostMapping
    fun registration(
        @RequestBody request: CreateUserRequest,
        @Autowired uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<CreateUserResponse> {
        val response = CreateUserResponse(userService.create(request.user))

        val uri = uriComponentsBuilder.path("/api/users").build().toUri()
        return ResponseEntity.created(uri).body(response)
    }
}