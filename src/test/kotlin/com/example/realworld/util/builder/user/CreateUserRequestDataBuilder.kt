package com.example.realworld.util.builder.user

import com.example.realworld.dto.user.request.CreateUserRequestData
import com.example.realworld.util.FakerPtBr

class CreateUserRequestDataBuilder {
    private val instance: CreateUserRequestData

    init {
        val faker = FakerPtBr.generate()
        instance = CreateUserRequestData(
            userName = faker.name().username(),
            email = faker.internet().emailAddress(),
            password = faker.internet().password()
        )
    }

    fun build(): CreateUserRequestData {
        return instance
    }
}