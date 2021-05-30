package com.example.realworld.util.builder.user

import com.example.realworld.dto.user.response.LoginResponseData
import com.example.realworld.util.FakerPtBr
import com.example.realworld.util.builder.common.StringBuilder

class LoginResponseDataBuilder {
    private val instance: LoginResponseData

    init {
        val faker = FakerPtBr.generate()
        instance = LoginResponseData(
            userName = faker.name().username(),
            email = faker.internet().emailAddress(),
            token = StringBuilder().build(),
            bio = faker.lorem().paragraph(),
            image = faker.internet().image()
        )
    }

    fun build(): LoginResponseData {
        return instance
    }
}