package com.example.realworld.util.builder.user

import com.example.realworld.dto.user.request.UpdateUserRequestData
import com.example.realworld.util.FakerPtBr

class UpdateUserRequestDataBuilder {
    private val faker = FakerPtBr.generate()
    private val instance = UpdateUserRequestData(
        userName = faker.name().username(),
        email = faker.internet().emailAddress(),
        bio = faker.lorem().paragraph(),
        image = faker.internet().image(),
        password = faker.internet().password()
    )

    fun build(): UpdateUserRequestData {
        return instance
    }
}
