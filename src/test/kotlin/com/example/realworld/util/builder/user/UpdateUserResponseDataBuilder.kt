package com.example.realworld.util.builder.user

import com.example.realworld.dto.user.response.UpdateUserResponseData
import com.example.realworld.util.FakerPtBr
import com.example.realworld.util.builder.common.StringBuilder

class UpdateUserResponseDataBuilder {
    private val faker = FakerPtBr.generate()

    fun build(): UpdateUserResponseData {
        return UpdateUserResponseData(
            userName = faker.name().username(),
            email = faker.internet().emailAddress(),
            bio = faker.lorem().paragraph(),
            image = faker.internet().image(),
            token = StringBuilder().build()
        )
    }
}
