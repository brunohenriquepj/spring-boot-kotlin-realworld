package com.example.realworld.util.builder.user

import com.example.realworld.dto.user.request.LoginRequestData
import com.example.realworld.util.FakerPtBr

class LoginRequestDataBuilder {
    private val instance: LoginRequestData

    init {
        val faker = FakerPtBr.generate()
        instance = LoginRequestData(
            email = faker.internet().emailAddress(),
            password = faker.internet().password()
        )
    }

    fun build(): LoginRequestData {
        return instance
    }
}
