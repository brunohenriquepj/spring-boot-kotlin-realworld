package com.example.realworld.controller

import com.example.realworld.dto.user.response.GetCurrentUserResponse
import com.example.realworld.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserInfoController(private val userService: UserService) {
    @GetMapping
    fun getCurrentUser(): ResponseEntity<GetCurrentUserResponse> {
        val user = userService.getCurrentUser()
        return ResponseEntity.ok(GetCurrentUserResponse(user))
    }
}
