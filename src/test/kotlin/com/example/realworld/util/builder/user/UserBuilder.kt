package com.example.realworld.util.builder.user

import com.example.realworld.entity.User
import com.example.realworld.util.FakerPtBr

class UserBuilder {
    private val faker = FakerPtBr.generate()

    var email: String? = null
    var userName: String? = null

    fun build(): User {
        return User(
            email = email ?: faker.internet().emailAddress(),
            userName = userName ?: faker.name().username(),
            password = faker.internet().password(),
            bio = faker.lorem().paragraph(),
            image = faker.internet().image(),
            id = faker.random().nextLong()
        )
    }
}
