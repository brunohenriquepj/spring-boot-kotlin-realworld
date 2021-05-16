package com.example.realworld.controller

import com.example.realworld.dto.Hello
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping
    fun hello(): ResponseEntity<Hello> {
        return ResponseEntity.ok(Hello("Hello World!"))
    }
}

