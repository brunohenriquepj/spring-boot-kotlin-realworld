package com.example.realworld.util.builder.user

import com.example.realworld.entity.User
import com.example.realworld.util.FakerPtBr

class UserBuilder {
    private val instance: User

    init {
        val faker = FakerPtBr.generate()
        instance = User(
            email = faker.internet().emailAddress(),
            userName = faker.name().username(),
            password = faker.internet().password(),
            bio = faker.lorem().paragraph(),
            image = faker.internet().image()
        )
    }

    fun build(): User {
        return instance
    }
}