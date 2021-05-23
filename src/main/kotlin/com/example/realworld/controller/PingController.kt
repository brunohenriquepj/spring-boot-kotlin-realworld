package com.example.realworld.controller

import com.example.realworld.dto.PingResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController {
    @GetMapping
    fun ping(): ResponseEntity<PingResponse> {
        return ResponseEntity.ok(PingResponse.of("Ok!"))
    }
}