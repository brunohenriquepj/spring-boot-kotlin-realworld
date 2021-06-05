package com.example.realworld.util.builder.user

import com.example.realworld.dto.user.response.CreateUserResponseData
import com.example.realworld.util.FakerPtBr
import java.util.*

class CreateUserResponseDataBuilder {
    private val instance: CreateUserResponseData

    init {
        val faker = FakerPtBr.generate()
        instance = CreateUserResponseData(
            userName = faker.name().username(),
            email = faker.internet().emailAddress(),
            token = UUID.randomUUID().toString(),
            bio = faker.lorem().paragraph(),
            image = faker.internet().image()
        )
    }

    fun build(): CreateUserResponseData {
        return instance
    }
}