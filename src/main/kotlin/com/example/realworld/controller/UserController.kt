package com.example.realworld.controller

import com.example.realworld.dto.user.request.UpdateUserRequest
import com.example.realworld.dto.user.response.GetCurrentUserResponse
import com.example.realworld.dto.user.response.UpdateUserResponse
import com.example.realworld.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {
    @GetMapping
    fun getCurrentUser(): ResponseEntity<GetCurrentUserResponse> {
        val user = userService.getCurrentUser()
        return ResponseEntity.ok(GetCurrentUserResponse(user))
    }

    @PutMapping
    fun updateUser(@RequestBody @Valid request: UpdateUserRequest): ResponseEntity<UpdateUserResponse> {
        val user = userService.update(request.user!!)
        return ResponseEntity.ok(UpdateUserResponse(user))
    }
}
